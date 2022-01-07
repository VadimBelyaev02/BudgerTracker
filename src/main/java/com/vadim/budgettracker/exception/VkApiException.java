package com.vadim.budgettracker.exception;

public class VkApiException extends RuntimeException {

    public VkApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
