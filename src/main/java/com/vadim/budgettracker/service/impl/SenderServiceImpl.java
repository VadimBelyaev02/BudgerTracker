package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.service.MailSenderService;
import com.vadim.budgettracker.service.SenderService;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService {

    private final MailSenderService mailSenderService;

    public SenderServiceImpl(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @Override
    public void sendEmail(String subject, String email, String message) {
        mailSenderService.sendMessage(subject, email, message);
    }
}
