package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.exception.MailSendingException;
import com.vadim.budgettracker.mail.MailSessionFactory;
import com.vadim.budgettracker.service.MailSenderService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:properties/mail.properties")
public class MailSenderServiceImpl implements MailSenderService {

    private final MailSessionFactory mailSessionFactory;
    private MimeMessage mimeMessage;

    public MailSenderServiceImpl(MailSessionFactory mailSessionFactory) {
        this.mailSessionFactory = mailSessionFactory;
    }

    @Override
    public void sendButton(String subject, String email, String message) {
        mimeMessage = mailSessionFactory.createMimeMessage();

        String URL = "https://budgettrackerjsonholder.herokuapp.com/api/register/confirm?code=" + message;
        String text = "<html>" +
                "<head><title>"+subject+"</title></head>" +
                "<body>" +
                "<form method=\"post\" action=\"" + URL + "\">" +
                "<input type=\"submit\" value=\"Confirm\">" +
                "</form>" +
                "</body>" +
                "</html>";

        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(text, "text/html");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendingException("There was an exception during sending the message", e);
        }
    }

    public void sendText(String subject, String email, String message) {
        mimeMessage = mailSessionFactory.createMimeMessage();

        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendingException("There was an exception during sending the message", e);
        }
    }
}
