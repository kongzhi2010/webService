package husd.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import husd.framework.BooleanMessage;
import husd.web.service.ILoginService;

@Controller
public class LoginController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/toLogin/page")
    public String toLogin(String toUrl, HttpServletRequest request, HttpServletResponse response,
            ModelMap modelMap) throws IOException {
        LOGGER.info("[login-toLogin] toUrl is: {}", toUrl);
        if (loginService.isLogin(request)) {
            if (StringUtils.isBlank(toUrl)) {
                response.sendRedirect("/");
            } else {
                response.sendRedirect(toUrl);
            }
            return null;
        }
        modelMap.addAttribute("toUrl", toUrl);
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String toUrl, HttpServletResponse response, ModelMap modelMap,
            String userName, String password, String verificationCode) {
        LOGGER.info("[login-login] userName is: {} ,toUrl is: {}", userName, toUrl);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        BooleanMessage succ = loginService.login(response, userName, password, verificationCode);
        try {
            response.getWriter().write(succ.toJson());
        } catch (IOException e) {
            LOGGER.error("[login-login] error, userName is :{} ,e is: {} ", userName, e);
        }
        return null;
    }
}
