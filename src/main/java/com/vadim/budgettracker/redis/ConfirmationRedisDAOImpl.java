package com.vadim.budgettracker.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ConfirmationRedisDAOImpl implements ConfirmationRedisDAO {

    private final RedisTemplate<String, Object> redisTemplate;

    public ConfirmationRedisDAOImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String save(String email, String code) {
        redisTemplate.opsForValue().set(code, email);
        return code;
    }

    public String findEmailByCode(String code) {
        return (String) redisTemplate.opsForValue().get(code);
    }

    public void delete(String code) {
        redisTemplate.delete(code);
    }
}
