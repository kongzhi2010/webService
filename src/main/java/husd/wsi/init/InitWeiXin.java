package husd.wsi.init;

import husd.wsi.service.WeiXinSendMessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by shengdong on 2015/2/1.
 */
@Component
public class InitWeiXin {

    private Logger logger = Logger.getLogger(InitWeiXin.class);

    @Resource
    private WeiXinSendMessageService weiXinSendMessageService;

    @PostConstruct
    public void send0() {
        logger.info("send0.........");
        weiXinSendMessageService.sendTextMessage("@all", "2", "33", "1", true, "nihaoya qiyehao", true);
    }
}
