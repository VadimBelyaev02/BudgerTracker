package com.vadim.budgettracker.service;

public interface MailSenderService {

    void sendButton(String subject, String email, String code);

    void sendText(String subject, String email, String code);
}
