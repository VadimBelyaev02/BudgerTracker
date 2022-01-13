package com.vadim.budgettracker.service;

public interface SenderService {

    void sendEmail(String subject, String email, String message);
}
