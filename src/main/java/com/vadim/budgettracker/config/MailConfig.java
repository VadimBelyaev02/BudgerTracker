package com.vadim.budgettracker.config;

import com.vadim.budgettracker.exception.MailSendingException;
import com.vadim.budgettracker.service.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
@PropertySource("classpath:properties/mail.properties")
public class MailConfig {

    @Value("${mail.password}")
    private final String password = "ecqxfgeuetrlcsxi";
    private final String fromEmail = "sendermail83@gmail.com";

    @Bean
    public Session session() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
    }

    @Bean
    public MimeMessage mimeMessage() {
        MimeMessage message = new MimeMessage(session());
        try {
            message.setFrom(fromEmail);
        } catch (MessagingException e) {
            throw new MailSendingException("Something is wrong" , e);
        }
        return message;
    }
}


