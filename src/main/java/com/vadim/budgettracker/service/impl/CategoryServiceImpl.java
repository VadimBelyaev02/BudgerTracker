package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public CategoryDTO getById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return null;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
