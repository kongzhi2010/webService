package husd.wsi.controller;

import husd.wsi.model.User;
import husd.wsi.service.UserService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class UserEdit {

    @Resource
    private UserService userService;

    private Logger logger = Logger.getLogger(UserEdit.class);

    @RequestMapping(method = RequestMethod.GET, value = "/userEdit")
     public ModelAndView initForm(ModelMap map) {
        User user = new User("","","");
        return new ModelAndView("user/userEdit").addObject(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView preLogin(ModelMap map) {
        User user = new User("","","");
        return new ModelAndView("user/login").addObject(user);
    }

    @RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
    public ModelAndView addUserForm(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              RedirectAttributesModelMap modelMap) {
        User user = new User(username,password,email);
        modelMap.addFlashAttribute("username", username);
        modelMap.addFlashAttribute("password",password);
        return new ModelAndView("redirect:/user/login");
    }

    @RequestMapping(value = "/user/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(String username,
                        String password,
                        Model model) {
        if(username== null && password == null){
            username = (String)model.asMap().get("username");
            password = (String)model.asMap().get("password");
        }
        User user = userService.verifyUser(username,password);
        if (user == null) {
            return "user/userEdit";
        }
        model.addAttribute(user);
        return "user/userProfile";
    }

    @RequestMapping("/user/showAllUser")
    public ModelAndView findAllUsers(){
        List users = userService.findAllUsers();
        return new ModelAndView("user/allUser","users",users);
    }
}
