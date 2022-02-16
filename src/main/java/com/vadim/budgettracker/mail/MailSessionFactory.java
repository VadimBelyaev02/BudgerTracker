package com.vadim.budgettracker.mail;


import com.vadim.budgettracker.exception.MailSendingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;

@Component
@PropertySource("classpath:properties/mail.properties")
public class MailSessionFactory {

    @Value("${mail.password}")
    private String password;

    @Value("${mail.from}")
    private String fromEmail;

    private final Environment env;
    private Session session;

    public MailSessionFactory(Environment env) {
        this.env = env;
    }

    public Session getSession() {
        if (Objects.isNull(session)) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", env.getRequiredProperty("mail.smtp.auth"));
            props.put("mail.smtp.starttls.enable", env.getRequiredProperty("mail.smtp.starttls.enable"));
            props.put("mail.smtp.host", env.getRequiredProperty("mail.smtp.host"));
            props.put("mail.smtp.port", env.getRequiredProperty("mail.smtp.port"));
            session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
        }
        return session;
    }

    public MimeMessage createMimeMessage() {
        MimeMessage message = new MimeMessage(getSession());
        try {
            message.setFrom(fromEmail);
        } catch (MessagingException e) {
            throw new MailSendingException("Something is wrong" , e);
        }
        return message;
    }
}
