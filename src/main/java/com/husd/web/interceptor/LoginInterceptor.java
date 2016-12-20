package com.husd.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.husd.framework.util.HttpUtil;
import com.husd.web.service.ILoginService;

public class LoginInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private ILoginService loginService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
            Exception exception) throws Exception {}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        if (loginService.isLoginUrl(request)) {
            return true;
        }
        boolean isLogin = loginService.isLoginAndUpdateLoginInfoIfTrue(request, response);
        if (!isLogin) {
            String currentUrl = HttpUtil.getRequestURLWithParam(request);
            String loginUrl = "/login/toLogin/page?toUrl=";
            if (StringUtils.isNotBlank(currentUrl)) {
                loginUrl = loginUrl + currentUrl;
            }
            response.sendRedirect(loginUrl);
            return false;
        }
        return true;
    }

}
