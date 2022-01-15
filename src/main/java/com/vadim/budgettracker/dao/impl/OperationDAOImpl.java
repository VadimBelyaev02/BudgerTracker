package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.OperationDAO;
import com.vadim.budgettracker.entity.Operation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationDAOImpl implements OperationDAO {

    private final EntityManager manager;

    public OperationDAOImpl(@Qualifier("entityManager") EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Operation> findAll() {
        return manager.createQuery("SELECT u FROM Operation u", Operation.class)
                .getResultList();
    }

    @Override
    public Optional<Operation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public Operation save(Operation operation) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Operation update(Operation operation) {
        return null;
    }
}
