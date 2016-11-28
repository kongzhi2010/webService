package husd.web.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import husd.framework.BooleanMessage;
import husd.framework.model.CookieEnum;
import husd.framework.util.CookieUtil;
import husd.web.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

    @Override
    public boolean isLogin(HttpServletRequest request) {
        String password = CookieUtil.getCookieByName(request, CookieEnum.USER_COOKIE.getName());
        return StringUtils.equals(password, "123");
    }

    @Override
    public BooleanMessage login(HttpServletResponse response, String userName, String password,
            String verificationCode) {
        CookieUtil.setCookie(response, CookieEnum.USER_COOKIE.getName(), "123", -1);
        return new BooleanMessage(true, "登陆成功！");
    }

}
