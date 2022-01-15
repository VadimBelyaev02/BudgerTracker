package com.vadim.budgettracker.config;

import com.vadim.budgettracker.exception.MailSendingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
@PropertySource("classpath:properties/mail.properties")
public class MailConfig {

    @Value("${mail.password}")
    private String password;
    private final String fromEmail = "sendermail83@gmail.com";
    private final Environment env;

    public MailConfig(Environment environment) {
        this.env = environment;
    }

    @Bean
    public Session session() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", env.getRequiredProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getRequiredProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", env.getRequiredProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getRequiredProperty("mail.smtp.port"));
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


