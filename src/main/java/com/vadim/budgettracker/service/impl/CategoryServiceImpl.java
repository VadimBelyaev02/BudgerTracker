package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.converter.CategoryConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public CategoryDTO getById(Long id) {
        Category category = categoryDAO.findById(id).orElseThrow(() ->
                new NotFoundException("Category with id=" + id + " is not found")
        );
        return categoryConverter.convertToDTO(category);
    }

    @Override
    @Transactional
    public List<CategoryDTO> getAll() {
        return categoryDAO.findAll().stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDAO.existsByName(categoryDTO.getName())) {
            throw new AlreadyExistsException("Category with name=" + categoryDTO.getName() + " already exists");
        }
        // have to fix a bug that id is not null, but has to be because it can't persist it because
        // it thinks that object had been in database, but at some moment it was detached,
        // or something like this, can't be sure
        Category category = categoryDAO.save(categoryConverter.convertToEntity(categoryDTO));
        category.setId(null);
        return categoryConverter.convertToDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO) {
        if (!categoryDAO.existsById(categoryDTO.getId())) {
            throw new NotFoundException("Category with id=" + categoryDTO.getId() + " is not found");
        }
        Category category = categoryDAO.update(categoryConverter.convertToEntity(categoryDTO));
        return categoryConverter.convertToDTO(category);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!categoryDAO.existsById(id)) {
            throw new NotFoundException("Category with id=" + id + " is not found");
        }
        categoryDAO.deleteById(id);
    }
}
