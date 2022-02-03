package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.exception.MailSendingException;
import com.vadim.budgettracker.service.MailSenderService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:properties/mail.properties")
public class MailSenderServiceImpl implements MailSenderService {

    private final MimeMessage mimeMessage;

    public MailSenderServiceImpl(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    @Override
    public void sendButton(String subject, String email, String message) {
        //String URL = "http://localhost:8080/api/register/confirm?code=";

        String URL = "https://budgettrackerjsonholder.herokuapp.com/api/register/confirm?code=" + message;
        String text = "<html>" +
                "<head><title>"+subject+"</title></head>" +
                "<body>" +
                "<form method=\"post\" action=\"" + URL + "\">" +
                "<input type=\"submit\" value=\"Confirm\">" +
                "</form>" +
                "</body>" +
                "</html>";
        //                String link = URL + message;
//        String text = "<html>" +
//                "<head><title>"+subject+"</title></head>" +
//                "<body>" +
//                "<p>" + message + "</p>" +
//                "</body>" +
//                "</html>";

//        String text = "<html>" +
//                "<head><title>"+subject+"</title></head>" +
//                "<body>" +
//                "Click <a href=\"" + link + "\">here</a> to confirm your account." +
//                "</body>" +
//                "</html>";

        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(text, "text/html");
        //    mimeMessage.setText(text);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendingException("There was an exception during sending the message", e);
        }
    }

    public void sendText(String subject, String email, String message) {
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
     //       mimeMessage.setContent(text, "text/html");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendingException("There was an exception during sending the message", e);
        }
    }
}
