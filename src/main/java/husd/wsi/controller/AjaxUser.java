package husd.wsi.controller;

import husd.wsi.pojo.User;
import husd.wsi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AjaxUser {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/verifyUser", method = RequestMethod.GET)
    @ResponseBody
    public String verifyIsNameExist(@RequestParam String username, Model model) {
        User user = userService.findUser(username);
        return user == null ? "1" : "0";
    }

    @RequestMapping(value = "/user/verifyEmail", method = RequestMethod.GET)
    @ResponseBody
    public String verifyEmail(@RequestParam String email, Model model) {
        return "0";
    }
}
