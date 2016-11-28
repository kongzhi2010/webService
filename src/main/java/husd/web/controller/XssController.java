package husd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hushengdong on 16/7/9.
 */
@Controller
public class XssController extends BaseController {

    @RequestMapping("/demo/xss/check/user/input")
    public String xss(String p, ModelMap modelMap) {
        modelMap.addAttribute("p", p);
        return "xss/xss1";
    }
}
