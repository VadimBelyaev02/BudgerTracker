package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;

import java.util.List;

public interface CategoryDAO extends CrudDAO<Category> {

    List<Operation> findAllByUserId(Long userId);

    Category getById(Long categoryId);
}
