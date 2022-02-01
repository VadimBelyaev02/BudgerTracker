package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.User;

import java.util.Optional;

public interface UserDAO extends CrudDAO<User> {

    boolean existsByEmailOrNickname(String email, String nickname);

    Optional<User> findByEmail(String email);

    User getById(Long userId);

    boolean existsByEmail(String email);
}
