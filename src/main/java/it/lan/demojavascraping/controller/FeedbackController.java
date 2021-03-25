package it.lan.demojavascraping.controller;

import it.lan.demojavascraping.config.EmailConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

@Controller
public class FeedbackController {
    private EmailConfiguration emailCfg;

    public FeedbackController(EmailConfiguration emailCfg) {
        this.emailCfg = emailCfg;
    }

    public void sendInfo(String info) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailCfg.getHost());
        mailSender.setPort(emailCfg.getPort());
        mailSender.setUsername(emailCfg.getUsername());
        mailSender.setPassword(emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("yuker97@gmail.com");
        mailMessage.setTo("noreply@scrapingdemo.com");
        mailMessage.setSubject("Update scraping");
        mailMessage.setText(info);

        // Send mail
        mailSender.send(mailMessage);
    }
}
