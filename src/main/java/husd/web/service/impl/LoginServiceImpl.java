package husd.web.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

import husd.framework.model.BooleanMessage;
import husd.framework.model.Constants;
import husd.framework.model.CookieEnum;
import husd.framework.model.LoginAuth;
import husd.framework.util.CookieUtil;
import husd.framework.util.Md5Util;
import husd.web.dao.UserDao;
import husd.web.model.User;
import husd.web.service.ICacheService;
import husd.web.service.ILoginService;

/**
 * 1）在cookie中，保存三个东西——用户名，登录序列，登录token。
 * 
 * 用户名：明文存放。 登录序列：一个被MD5散列过的随机数，仅当强制用户输入口令时更新（如：用户修改了口令）。
 * 登录token：一个被MD5散列过的随机数，仅一个登录session内有效，新的登录session会更新它。
 * 
 * 2）上述三个东西会存在服务器上，服务器的验证用户需要验证客户端cookie里的这三个事。
 * 
 * 3）这样的设计会有什么样的效果，会有下面的效果，
 * 
 * a）登录token是单实例登录。意思就是一个用户只能有一个登录实例。
 * 
 * b）登录序列是用来做盗用行为检测的。如果用户的cookie被盗后，盗用者使用这个cookie访问网站时，我们的系统是以为是合法用户，然后更新“登录token”，而真正的用户回来访问时，
 * 系统发现只有“用户名”和“登录序列”相同，但是“登录token” 不对，这样的话，系统就知道，这个用户可能出现了被盗用的情况，于是，系统可以清除并更改登录序列 和
 * 登录token，这样就可以令所有的cookie失效，并要求用户输入口令。并给警告用户系统安全。
 * 
 * 4）当然，上述这样的设计还是会有一些问题，比如：同一用户的不同设备登录，甚至在同一个设备上使用不同的浏览器保登录。一个设备会让另一个设备的登录token和登录序列失效，
 * 从而让其它设备和浏览器需要重新登录，并会造成cookie被盗用的假象。所以，你在服务器服还需要考虑- IP 地址，
 * 
 * a) 如果以口令方式登录，我们无需更新服务器的“登录序列”和 “登录token”（但需要更新cookie）。因为我们认为口令只有真正的用户知道。
 * 
 * b) 如果 IP相同 ，那么，我们无需更新服务器的“登录序列”和
 * “登录token”（但需要更新cookie）。因为我们认为是同一用户有同一IP（当然，同一个局域网里也有同一IP，但我们认为这个局域网是用户可以控制的。网吧内并不推荐使用这一功能）。
 * 
 * c) 如果 （IP不同 && 没有用口令登录），那么，“登录token”
 * 就会在多个IP间发生变化（登录token在两个或多个ip间被来来回回的变换），当在一定时间内达到一定次数后，系统才会真正觉得被盗用的可能性很高，此时系统在后台清除“登录序列”和“登录token“
 * ，让Cookie失效，强制用户输入口令（或是要求用户更改口令），以保证多台设备上的cookie一致。
 * 
 * @author hushengdong
 *
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final static Gson gson = new Gson();

    @Autowired
    private UserDao userDao;

    @Resource
    private ICacheService cacheService;

    @Override
    public boolean isLoginAndUpdateLoginInfoIfTrue(HttpServletRequest request,
            HttpServletResponse response) {
        LoginAuth loginAuthInCookie = getLoginAuthFromCookie(request);
        String username = loginAuthInCookie.getUsername();
        String json = cacheService.getValueByName(getLoginName(username));
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        LoginAuth loginAuth = gson.fromJson(json, LoginAuth.class);
        if (loginAuth.equalsWithAnother(loginAuthInCookie)) {
            // 登陆成功之后，更新cookie和缓存里面的loginToken。
            // 更新过期时间
            String newLoginToken = createLoginToken(username, loginAuth.getLoginToken());
            loginAuth.setLoginToken(newLoginToken);
            String newJson = loginAuth.toJson();
            putLoginAuthIntoCache(username, newJson);
            putLoginInfoIntoCookie(response, newJson);
            return true;
        }
        if (loginAuth.isPasswordMayBeStolen(loginAuthInCookie)) {
            logout(username);
        }
        return false;
    }


    @Override
    public BooleanMessage login(HttpServletRequest request, HttpServletResponse response,
            String username, String password, String verificationCode) {
        // 思考下登陆系统需要干什么事
        // 思考下防止频繁访问的系统应该放在什么地方
        // 1、如果已经登陆了，就直接返回成功，不需要判断了。
        if (isLoginAndUpdateLoginInfoIfTrue(request, response)) {
            return new BooleanMessage(true, "您当前已经是登陆状态，无需重复登陆！");
        }
        User user = null;
        if (StringUtils.equals(username, "hushengdong")) {
            user = new User();
            user.setUsername("hushengdong");
            user.setSalt("123");
            String pass = getMd5encodedPassword("hushengdong", "12345", "123");
            user.setPassword(pass);
        } else {
            user = queryUserByUsername(username);
            if (user == null) {
                return new BooleanMessage(false, "登陆失败，用户名不存在！");
            }
        }

        // 2、如果没有登陆，判断下用户的密码。
        BooleanMessage passwordCorrect = isPasswordCorrect(user, password);
        if (passwordCorrect.isSucc()) {
            // 登陆成功了之后写入登陆信息。
            recordLoginInfo(user, response);
        }
        return passwordCorrect;
    }

    @Override
    public boolean isLoginUrl(HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        return currentUrl != null && currentUrl.startsWith("login");
    }

    private BooleanMessage isPasswordCorrect(User user, String password) {
        String realPassword = getMd5encodedPassword(user.getUsername(), password, user.getSalt());
        String expectedPassword = user.getPassword();
        boolean correct = StringUtils.equals(expectedPassword, realPassword);
        if (correct) {
            return new BooleanMessage(true, "登陆成功！");
        } else {
            return new BooleanMessage(false, "登陆失败，用户名或密码不正确，请重新输入！");
        }
    }

    private String getMd5encodedPassword(String username, String password, String salt) {
        String str = username + password + salt;
        String realPassword = Md5Util.encode(str);
        return realPassword;
    }

    private User queryUserByUsername(String username) {
        List<User> userList = userDao.queryUserByUsername(username);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    private void recordLoginInfo(User user, HttpServletResponse response) {
        // 这个方法是登陆系统的关键，我们怎么标示这个用户已经登陆了，要防止别人窃取了cookie之后
        // 可以异地登陆用户的账号。
        String username = user.getUsername();
        String loginSeq = createLoginSeq(username, user.getSalt());
        String loginToken = createLoginToken(username, user.getSalt());
        LoginAuth loginAuth = new LoginAuth(username, loginSeq, loginToken);
        String json = loginAuth.toJson();
        putLoginAuthIntoCache(username, json);
        putLoginInfoIntoCookie(response, json);
    }

    private void putLoginAuthIntoCache(String username, String json) {
        ICacheService cacheService = new DefaultCacheServiceImpl();
        cacheService.setValueByName(getLoginName(username), json, Constants.LOING_EXPIRED_TIME);
    }

    private void putLoginInfoIntoCookie(HttpServletResponse response, String json) {
        CookieUtil.setCookie(response, CookieEnum.USER_NAME.getName(), json,
                Constants.LOING_EXPIRED_TIME);
    }

    private LoginAuth getLoginAuthFromCookie(HttpServletRequest request) {
        String json = CookieUtil.getCookieByName(request, CookieEnum.USER_NAME.getName());
        if (StringUtils.isBlank(json)) {
            return new LoginAuth();
        }
        return gson.fromJson(json, LoginAuth.class);
    }

    private String createLoginToken(String username, String salt) {
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return Md5Util.encode(username + salt + randomInt);
    }

    private String createLoginSeq(String username, String salt) {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        String temp = Md5Util.encode(username + salt + randomInt);
        return Md5Util.encode(temp);
    }

    private String getLoginName(String username) {
        return username + "@" + CookieEnum.USER_NAME.getName();
    }

    private String getLoginSeqName(String username) {
        return username + "@" + CookieEnum.USER_LOGIN_SEQ.getName();
    }

    private String getLoginTokenName(String username) {
        return username + "@" + CookieEnum.USER_LOGIN_TOKEN.getName();
    }

    private void updateLoginExpiredTime(String username) {
        long currentTime = System.currentTimeMillis();
        long exptectedExpiredTime = currentTime + Constants.LOING_EXPIRED_TIME;
        cacheService.updateExpiredTime(getLoginName(username), exptectedExpiredTime);
        String loginSeqName = getLoginSeqName(username);
        cacheService.updateExpiredTime(loginSeqName, exptectedExpiredTime);
        String loginTokenName = getLoginTokenName(username);
        cacheService.updateExpiredTime(loginTokenName, exptectedExpiredTime);
    }

    private void updateLoginToken(String username, String newLoginToken) {
        String loginName = getLoginTokenName(username);
        cacheService.setValueByName(loginName, newLoginToken);
    }

    @Override
    public boolean logout(String username) {
        cacheService.removeName(username);
        cacheService.removeName(getLoginSeqName(username));
        cacheService.removeName(getLoginTokenName(username));
        return true;
    }
}
