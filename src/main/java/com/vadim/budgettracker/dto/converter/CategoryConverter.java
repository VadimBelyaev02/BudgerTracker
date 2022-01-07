package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category convertToEntity(CategoryDTO categoryDTO) {
        final Long id = categoryDTO.getId();
        final String name = categoryDTO.getName();
        return Category.builder()
                .id(id)
                .name(name)
                .build();
    }

    public CategoryDTO convertToDTO(Category category) {
        final Long id = category.getId();
        final String name = category.getName();
        return CategoryDTO.builder()
                .id(id)
                .name(name)
                .build();
    }
}
