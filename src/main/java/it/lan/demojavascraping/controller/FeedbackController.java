package it.lan.demojavascraping.controller;

import it.lan.demojavascraping.config.EmailConfiguration;
import it.lan.demojavascraping.model.Feedback;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private EmailConfiguration emailCfg;

    public FeedbackController(EmailConfiguration emailCfg) {
        this.emailCfg = emailCfg;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("noreply@scrapingdemo.com");
        mailMessage.setSubject("Update scraping");
        mailMessage.setText(feedback.getFeedback());

        // Send mail
        mailSender.send(mailMessage);
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
