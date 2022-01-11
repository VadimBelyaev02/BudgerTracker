package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByUserId(Long userId);

}
