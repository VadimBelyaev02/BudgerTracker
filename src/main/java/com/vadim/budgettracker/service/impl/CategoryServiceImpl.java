package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.CategoryConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Permission;
import com.vadim.budgettracker.exception.AccessDeniedException;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.security.AuthenticatedUserFactory;
import com.vadim.budgettracker.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final CategoryConverter categoryConverter;
    private final AuthenticatedUserFactory factory;

    public CategoryServiceImpl(CategoryDAO categoryDAO, CategoryConverter categoryConverter, AuthenticatedUserFactory factory) {
        this.categoryDAO = categoryDAO;
        this.categoryConverter = categoryConverter;
        this.factory = factory;
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
    @Transactional()
    public CategoryDTO save(CategoryDTO categoryDTO) {
        UserDTO userDTO = factory.currentUser();
        if (!userDTO.getConfirmed()) {
                throw new AccessDeniedException("User with email = " + userDTO.getEmail() + " is not confirmed");
        }
        if (categoryDAO.existsByName(categoryDTO.getName())) {
            throw new AlreadyExistsException("Category with name=" + categoryDTO.getName() + " already exists");
        }
        // have to fix a bug that id is not null, but has to be because it can't persist it because
        // it thinks that object had been in database, but at some moment it was detached,
        // or something like this, can't be sure
        Category category = categoryDAO.save(categoryConverter.convertToEntity(categoryDTO));
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
        Category category = categoryDAO.findById(id).orElseThrow(() ->
                new NotFoundException("Category with id=" + id + " is not found")
        );
        UserDTO currentUser = factory.currentUser();
        if (Objects.equals(currentUser.getId(), category.getUserId()) &&
                !currentUser.hasPermission(Permission.DELETE)) {
            throw new AccessDeniedException("You can't delete someone else's category");
        }
        categoryDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<CategoryDTO> getAllByUserId(Long userId) {
        return categoryDAO.findAllByUserId(userId).stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
