package husd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import husd.web.service.ILoginService;

public class LoginInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private ILoginService loginService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object obj, Exception exception) throws Exception {
        LOGGER.info("after completion");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj,
            ModelAndView modelAndView) throws Exception {
        LOGGER.info("post Handle");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
            throws Exception {
        LOGGER.info("pre Handle");
        String currentUrl = request.getRequestURI();
        if (currentUrl != null && currentUrl.startsWith("/toLogin/page")) {
            return true;
        }
        boolean isLogin = loginService.isLogin(request);
        if (!isLogin) {
            String referer = request.getParameter("Referer");
            String loginUrl = "/toLogin/page?toUrl=";
            if (StringUtils.isNotBlank(referer)) {
                loginUrl = loginUrl + referer;
            }
            response.sendRedirect(loginUrl);
            return false;
        }
        return true;
    }

}
