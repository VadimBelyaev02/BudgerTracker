package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByUserId(Long userId);

}
