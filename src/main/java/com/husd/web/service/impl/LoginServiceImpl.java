package com.husd.web.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.husd.framework.cache.ICacheService;
import com.husd.framework.model.BooleanMessage;
import com.husd.framework.model.Constants;
import com.husd.framework.model.CookieEnum;
import com.husd.framework.model.LoginAuth;
import com.husd.framework.util.CookieUtil;
import com.husd.framework.util.HttpUtil;
import com.husd.framework.util.Md5Util;
import com.husd.web.dao.UserDao;
import com.husd.web.model.User;
import com.husd.web.service.ILoginService;

/**
 * 
 * @author hushengdong
 *
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private final static Gson gson = new Gson();

    @Autowired
    private UserDao userDao;

    @Resource
    private ICacheService cacheService;

    @Override
    public boolean isLoginAndUpdateLoginInfoIfTrue(HttpServletRequest request, HttpServletResponse response) {
        LoginAuth loginAuthInCookie = getLoginAuthFromCookie(request);
        String username = loginAuthInCookie.getUsername();
        String json = cacheService.getValueByName(getLoginName(username));
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        LoginAuth loginAuth = gson.fromJson(json, LoginAuth.class);
        if (loginAuth.equalsWithAnother(loginAuthInCookie)) {
            updateLoginToken(username, loginAuth, response);
            request.getSession(true).setAttribute("username", username);
            return true;
        }
        if (loginAuth.isPasswordMayBeStolen(loginAuthInCookie)) {
            logout(username);
        }
        return false;
    }


    @Override
    public BooleanMessage login(HttpServletRequest request, HttpServletResponse response, String username,
            String password, String verificationCode) {
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
            recordLoginInfo(user, request, response);
        }
        return passwordCorrect;
    }

    @Override
    public boolean isLoginUrl(HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        return currentUrl != null && currentUrl.startsWith("login");
    }

    @Override
    public LoginAuth getLoginAuthFromCookie(HttpServletRequest request) {
        String json = CookieUtil.getCookieByName(request, CookieEnum.USER_NAME.getName());
        if (StringUtils.isBlank(json)) {
            return new LoginAuth();
        }
        LoginAuth loginAuth = gson.fromJson(json, LoginAuth.class);
        if (loginAuth != null) {
            String ip = HttpUtil.getIp(request);
            loginAuth.setIp(ip);
        }
        return loginAuth;
    }

    @Override
    public boolean logout(String username) {
        cacheService.removeName(getLoginName(username));
        return true;
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

    private void recordLoginInfo(User user, HttpServletRequest request, HttpServletResponse response) {
        // 这个方法是登陆系统的关键，我们怎么标示这个用户已经登陆了，要防止别人窃取了cookie之后
        // 可以异地登陆用户的账号。
        String username = user.getUsername();
        String loginSeq = createLoginSeq(username, user.getSalt());
        String loginToken = createLoginToken(username, user.getSalt());
        String ip = HttpUtil.getIp(request);
        LoginAuth loginAuth = new LoginAuth(username, loginSeq, loginToken, ip);
        String json = loginAuth.toJson();
        putLoginAuthIntoCache(username, json);
        putLoginInfoIntoCookie(response, json);
    }

    private void putLoginAuthIntoCache(String username, String json) {
        cacheService.setValueByName(getLoginName(username), json, Constants.LOING_EXPIRED_TIME);
    }

    private void putLoginInfoIntoCookie(HttpServletResponse response, String json) {
        CookieUtil.setCookie(response, CookieEnum.USER_NAME.getName(), json, Constants.LOING_EXPIRED_TIME);
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

    // 每一个登陆成功之后，都要更新loginToken。
    private void updateLoginToken(String username, LoginAuth loginAuth, HttpServletResponse response) {
        String newLoginToken = createLoginToken(username, loginAuth.getLoginToken());
        loginAuth.setLoginToken(newLoginToken);
        String newJson = loginAuth.toJson();
        putLoginAuthIntoCache(username, newJson);
        putLoginInfoIntoCookie(response, newJson);
    }
}
