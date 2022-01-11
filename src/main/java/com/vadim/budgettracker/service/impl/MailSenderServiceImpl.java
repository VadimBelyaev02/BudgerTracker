package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.exception.MailSendingException;
import com.vadim.budgettracker.service.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
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
    public void sendMessage(String subject, String email, String message) {

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
