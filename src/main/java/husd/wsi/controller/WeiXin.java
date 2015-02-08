package husd.wsi.controller;

import husd.wsi.service.WeiXinSendMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by shengdong on 2015/2/8.
 */
@Controller
public class WeiXin {

    @Resource
    private WeiXinSendMessageService weiXinSendMessageService;

    @RequestMapping(value = "/send1")
    @ResponseBody
    public String send1(@RequestParam String message) {
        return weiXinSendMessageService.sendTextMessage("@all", "1", "1", 1, message, true);
    }
}
