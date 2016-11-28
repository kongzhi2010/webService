package husd.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import husd.framework.BooleanMessage;

public interface ILoginService {

    /**
     * 通过cookie来判断当前用户是否处于登陆状态。
     * 
     * @param cookie
     * @return
     */
    public boolean isLogin(HttpServletRequest request);

    public BooleanMessage login(HttpServletResponse response, String userName, String password,
            String verificationCode);

}
