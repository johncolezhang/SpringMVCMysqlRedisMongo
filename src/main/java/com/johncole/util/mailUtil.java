package com.johncole.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static com.johncole.util.StringEnum.EMAIL_PASSWORD;
import static com.johncole.util.StringEnum.EMAIL_USER;
import static com.johncole.util.StringEnum.SEND_URL;

/**
 * Created by johncole on 2017/6/22.
 */
public class mailUtil {
    public static JavaMailSender initMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", "25000");
        sender.setJavaMailProperties(properties);
        sender.setHost("smtp.qq.com");
        sender.setUsername(EMAIL_USER);
        sender.setPassword(EMAIL_PASSWORD);
        sender.setPort(465);
        sender.setDefaultEncoding("UTF-8");
        return sender;
    }

    public static void sendEmail(JavaMailSender sender, String receiver, String auth_code) throws MessagingException {
        MimeMessage mail = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mail,true,"UTF-8");
        messageHelper.setTo(receiver);
        messageHelper.setFrom(EMAIL_USER + "@qq.com");
        messageHelper.setSentDate(new Date());
        messageHelper.setSubject("ZK的邮箱验证");
        messageHelper.setText("<html><title></title><body><h1><a href='" + SEND_URL + auth_code + "'>点我</a></h1><body></html>", true);
        sender.send(mail);
    }
}
