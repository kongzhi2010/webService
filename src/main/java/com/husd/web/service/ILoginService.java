package com.husd.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.husd.framework.model.BooleanMessage;
import com.husd.framework.model.LoginAuth;

public interface ILoginService {

    /**
     * 通过cookie来判断当前用户是否处于登陆状态。
     * 
     * @param cookie
     * @return
     */
    public boolean isLoginAndUpdateLoginInfoIfTrue(HttpServletRequest request,
            HttpServletResponse response);

    /**
     * 登陆
     * 
     * @param response
     * @param userName
     * @param password
     * @param verificationCode
     * @return
     */
    public BooleanMessage login(HttpServletRequest request, HttpServletResponse response,
            String userName, String password, String verificationCode);

    /**
     * 判断当前的url地址是否是登陆的URL
     * 
     * @param request
     * @return
     */
    public boolean isLoginUrl(HttpServletRequest request);

    public boolean logout(String username);

    public LoginAuth getLoginAuthFromCookie(HttpServletRequest request);

}
