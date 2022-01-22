package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.Confirmation;
import com.vadim.budgettracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    Optional<Confirmation> findByCode(String code);

    void deleteByCode(String code);


    //findUserByCode
}
