package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    private final UserDAO userDAO;

    public CategoryConverter(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        final Long id = categoryDTO.getId();
        final String name = categoryDTO.getName();
        final String section = categoryDTO.getSection();
        final String color = categoryDTO.getColor();
        final String logo = categoryDTO.getLogo();
        final User user = userDAO.getById(categoryDTO.getUserId());
        return Category.builder()
                .section(section)
                .color(color)
                .logo(logo)
                .user(user)
                .id(id)
                .name(name)
                .build();
    }

    public CategoryDTO convertToDTO(Category category) {
        final Long id = category.getId();
        final String name = category.getName();
        final String section = category.getSection();
        final String color = category.getColor();
        final String logo = category.getLogo();
        final Long userId = category.getUser().getId();
        return CategoryDTO.builder()
                .section(section)
                .color(color)
                .logo(logo)
                .userId(userId)
                .id(id)
                .name(name)
                .build();
    }
}
/*
    @Enumerated(value = EnumType.STRING)
    private Section section;

    @NotBlank
    private String color;

    @NotBlank
    private String logo;

    @NotNull
    private Long userId;
 */