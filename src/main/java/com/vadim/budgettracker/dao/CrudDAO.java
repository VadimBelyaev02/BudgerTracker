package com.vadim.budgettracker.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void deleteById(Long id);

    T update(T t);
}
