package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.converter.CategoryConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryDAO categoryDAO, CategoryConverter categoryConverter) {
        this.categoryDAO = categoryDAO;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public CategoryDTO getById(Long id) {
        Category category = categoryDAO.findById(id).orElseThrow(() ->
                new NotFoundException("Category with id=" + id + " is not found")
        );
        return categoryConverter.convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryDAO.findAll().stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDAO.existsById(categoryDTO.getId())) {
            throw new AlreadyExistsException("Category with id=" + categoryDTO.getId() + " already exists");
        }
        Category category = categoryDAO.save(categoryConverter.convertToEntity(categoryDTO));
        return categoryConverter.convertToDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        if (!categoryDAO.existsById(categoryDTO.getId())) {
            throw new NotFoundException("Category with id=" + categoryDTO.getId() + " is not found");
        }
        Category category = categoryDAO.update(categoryConverter.convertToEntity(categoryDTO));
        return categoryConverter.convertToDTO(category);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryDAO.existsById(id)) {
            throw new NotFoundException("Category with id=" + id + " is not found");
        }
        categoryDAO.deleteById(id);
    }
}
