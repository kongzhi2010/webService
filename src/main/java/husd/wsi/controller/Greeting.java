package husd.wsi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Greeting {
	
    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("message","world!");
        return "greeting";
    }
    
}