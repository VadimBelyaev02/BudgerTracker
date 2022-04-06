package com.vadim.budgettracker.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T, K> {

    List<T> findAll();

    Optional<T> findById(K id);

    boolean existsById(K id);

    T save(T t);

    void deleteById(K id);

    T update(T t);


}
