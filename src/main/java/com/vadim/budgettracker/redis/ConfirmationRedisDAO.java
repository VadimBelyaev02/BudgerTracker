package com.vadim.budgettracker.redis;

public interface ConfirmationRedisDAO {

    String save(String email, String code);

    String findEmailByCode(String code);

    void delete(String code);
}
