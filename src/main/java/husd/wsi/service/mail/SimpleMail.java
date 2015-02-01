package husd.wsi.service.mail;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by shengdong on 2015/1/17.
 */
@Service
public class SimpleMail {

    private Logger logger = Logger.getLogger(SimpleMail.class);

    @Resource
    private JavaMailSenderImpl javaMailSenderImpl;

    public void sendTextMail(String[] to,String from, String [] cc ,String subject,String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.timeout", 15000);
        javaMailSenderImpl.send(simpleMailMessage);
    }

    public void sendHtmlMail(String [] to,String from, String [] cc ,String subject,String text) {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(to);
            helper.setFrom(from);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(text,true);
            javaMailSenderImpl.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("MessagingException", e);
            throw new RuntimeException(e);
        }
    }

}
