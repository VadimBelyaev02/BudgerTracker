package com.vadim.budgettracker.unit;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.converter.CategoryConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.service.CategoryService;
import com.vadim.budgettracker.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UserService test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceUnitTest {

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryConverter converter;

    @Mock
    private CategoryDAO categoryDAO;

    @Test
    public void Given_ServiceTriesToFindCategory_When_GetCategoryId_Then_FoundCategoryIsReturned() {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        CategoryDTO categoryDTO = new CategoryDTO();

        when(categoryDAO.findById(id)).thenReturn(Optional.of(category));
        when(converter.convertToDTO(category)).thenReturn(categoryDTO);

        assertEquals(service.getById(id), categoryDTO);

        verify(categoryDAO, Mockito.only()).findById(id);
        verify(converter, Mockito.only()).convertToDTO(category);
    }



    /*


       @Test
    public void Given_ServiceTriesToFindUser_When_GetUserId_Then_FoundUserIsReturned() {
        Long id = 1L;
        String email = "vadimbelaev002@gmail.com";
        User user = new User();
        UserDTO userDTO = new UserDTO();
        user.setId(id);
        user.setEmail(email);

        Mockito.when(userDAO.findById(id)).thenReturn(Optional.of(user));

        Mockito.when(userConverter.convertToDTO(user)).thenReturn(userDTO);

        assertEquals(userService.getById(id), userDTO);

        Mockito.verify(userDAO, Mockito.only()).findById(id);
        Mockito.verify(userConverter, Mockito.only()).convertToDTO(user);
    }
     */
}
