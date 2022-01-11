package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.service.MailSenderService;
import com.vadim.budgettracker.service.SenderService;
import com.vadim.budgettracker.service.VkSenderService;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService {

    private final MailSenderService mailSenderService;
    private final VkSenderService vkSenderService;

    public SenderServiceImpl(MailSenderService mailSenderService, VkSenderService vkSenderService) {
        this.mailSenderService = mailSenderService;
        this.vkSenderService = vkSenderService;
    }

    @Override
    public void sendEmail(String subject, String email, String message) {
        mailSenderService.sendMessage(subject, email, message);
    }

    @Override
    public void sendVkMessage(String vkId, String message) {
        vkSenderService.sendMessage(vkId, message);
    }
}
