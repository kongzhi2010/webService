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

import husd.framework.model.BooleanMessage;
import husd.web.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/toLogin/page")
    public String toLogin(String toUrl, HttpServletRequest request, HttpServletResponse response,
            ModelMap modelMap) throws IOException {
        LOGGER.info("[login-toLogin] toUrl is: {}", toUrl);
        if (loginService.isLoginAndUpdateLoginInfoIfTrue(request, response)) {
            // 不知道该转向哪里或者本身就还是登陆页面的url，直接转向主页。
            if (StringUtils.isBlank(toUrl) || loginService.isLoginUrl(request)) {
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
    public String login(String toUrl, HttpServletRequest request, HttpServletResponse response,
            ModelMap modelMap, String username, String password, String verificationCode) {
        LOGGER.info("[login-login] username is: {} ,toUrl is: {}", username, toUrl);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        BooleanMessage succ =
                loginService.login(request, response, username, password, verificationCode);
        writeBackJson(response, succ.toJson());
        return null;
    }
}
