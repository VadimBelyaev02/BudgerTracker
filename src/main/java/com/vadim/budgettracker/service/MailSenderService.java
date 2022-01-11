package com.vadim.budgettracker.service;

public interface MailSenderService {

    void sendMessage(String subject, String email, String code);
}
