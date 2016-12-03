package husd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends BaseController{

    @RequestMapping("/error/404")
    public String toDemoPage() {
        return "404";
    }
}
