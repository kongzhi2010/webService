package com.husd.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.husd.web.model.DemoCondition;
import com.husd.web.model.DemoResult;
import com.husd.web.model.Pager;
import com.husd.web.service.IDemoService;

/**
 * Created by hushengdong on 16/6/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/demo/apply")
    public String toDemoPage(DemoCondition demoCondition, ModelMap modelMap) {
        LOGGER.info("[demo-apply]" + demoCondition.toString());
        // 这个是必须的,要把条件再传递过去.
        modelMap.addAttribute("condition", demoCondition);
        Pager<DemoResult> pager = demoService.queryDemoResult(demoCondition);
        modelMap.addAttribute("pager", pager);
        return "demo/apply";
    }
}
