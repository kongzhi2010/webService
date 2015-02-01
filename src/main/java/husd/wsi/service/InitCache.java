package husd.wsi.service;

import husd.wsi.service.mail.SimpleMail;
import husd.wsi.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by shengdong on 2015/1/11.
 */
@Component
public class InitCache {

    private Logger logger = Logger.getLogger(InitCache.class);

    @Resource
    private SimpleMail simpleMail;

    String[] mail = {"shengdonghu@126.com"};
    String[] cc = {"hu.shengdong.h@gmail.com", "husd@baojia.com"};

    @PostConstruct
    public void initCache() {
        logger.info("initCache");
      //  sendTextMail();
       // sendHtmlMail();
    }

    private void sendTextMail() {
        simpleMail.sendTextMail(mail, "shengdonghu@126.com", cc, "nihao", "this is a test message");
    }

    private void sendHtmlMail() {
        simpleMail.sendHtmlMail(mail, "shengdonghu@126.com", cc, "nihao", FileUtil.readFile("D:\\test.html"));
    }

}
