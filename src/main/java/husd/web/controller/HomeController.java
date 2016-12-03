package husd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import husd.web.model.UserProfile;

@Controller
public class HomeController extends BaseController {

    @RequestMapping({"/", "/home"})
    public String showHomePage(ModelMap modelMap) {
        modelMap.addAttribute("a1", "a1");
        Integer[] arr = {1, 2, 3, 4, 5};
        UserProfile user = new UserProfile();
        user.setEmail("shengdonghu@126.com");
        user.setUsername("hushengdong");
        user.setPassword("213");
        modelMap.addAttribute("arr", arr);
        modelMap.addAttribute("user", user);
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
