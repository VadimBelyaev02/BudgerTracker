package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDTO getById(Long id);

    List<CategoryDTO> getAll();

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void deleteById(Long id);
}
