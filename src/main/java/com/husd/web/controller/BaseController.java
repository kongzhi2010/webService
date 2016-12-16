package com.husd.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.husd.framework.model.LoginAuth;
import com.husd.web.service.ILoginService;

@Controller
public class BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected ILoginService loginService;

    protected void writeBackJson(HttpServletResponse response, String json) {
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("[base controller] error, json is : {} e is: {} ", json, e);
        }
    }

    // 因为只有登陆之后才需要获取用户的名字，所以一般来说都能获取到用户的username。
    protected String getCurrentUsername(HttpServletRequest request) {
        LoginAuth loginAuthInCookie = loginService.getLoginAuthFromCookie(request);
        if (loginAuthInCookie != null) {
            return loginAuthInCookie.getUsername();
        }
        return StringUtils.EMPTY;
    }
}
