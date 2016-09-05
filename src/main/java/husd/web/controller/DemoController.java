package husd.web.controller;

import husd.web.model.DemoCondition;
import husd.web.model.DemoResult;
import husd.web.model.Pager;
import husd.web.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hushengdong on 16/6/3.
 */
@Controller
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/demo/apply")
    public String toDemoPage(DemoCondition demoCondition, ModelMap modelMap) {
        logger.info("1 controller get condition: " + demoCondition.toString());
        //这个是必须的,要把条件再传递过去.
        modelMap.addAttribute("condition", demoCondition);
        Pager<DemoResult> pager = demoService.queryDemoResult(demoCondition);
        modelMap.addAttribute("pager", pager);
        return "demo/apply";
    }
}
