package com.husd.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/page")
public class DemoController extends BaseController {

    /**
     * 
     * 网站块状布局。
     * 
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("block")
    public String block(ModelMap modelMap, HttpServletRequest request) {
        return "demo/block";
    }

}
