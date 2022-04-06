package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.User;

import java.util.Optional;

public interface UserDAO extends CrudDAO<User, Long> {

    boolean existsByEmailAndNickname(String email, String nickname);

    Optional<User> findByEmail(String email);

    User getById(Long userId);

    boolean existsByEmail(String email);

    boolean existByNickname(String nickname);
}
