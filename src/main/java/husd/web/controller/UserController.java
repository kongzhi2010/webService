package husd.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profile")
public class UserController extends BaseController {

    @RequestMapping("toPage")
    public String userProfile(HttpServletRequest request, ModelMap modelMap) {
        // TODO 根据用户名查询用户信息
        String username = getCurrentUsername(request);
        modelMap.addAttribute("username", username);
        return "account/profile";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, ModelMap modelMap) {
        String username = getCurrentUsername(request);
        loginService.logout(username);
        return "login";
    }
}
