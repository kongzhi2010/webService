package com.husd.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.husd.web.model.UserProfile;

@Controller
public class HomeController extends BaseController {

    @RequestMapping({"/", "/home"})
    public String showHomePage(ModelMap modelMap, HttpServletRequest request) {
        String username = super.getCurrentUsername(request);
        modelMap.addAttribute("username", username);
        return "overview";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
