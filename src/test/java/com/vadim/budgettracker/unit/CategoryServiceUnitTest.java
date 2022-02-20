package com.vadim.budgettracker.unit;

import com.vadim.budgettracker.dao.impl.CategoryDAOImpl;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.CategoryConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.security.AuthenticatedUserFactory;
import com.vadim.budgettracker.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("CategoryService test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceUnitTest {

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryConverter converter;

    @Mock
    private CategoryDAOImpl categoryDAO;

    @Mock
    private AuthenticatedUserFactory userFactory;

    @Test
    public void Given_ServiceTriesToFindCategory_When_GetCategoryId_Then_FoundCategoryIsReturned() {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        CategoryDTO categoryDTO = new CategoryDTO();

        when(categoryDAO.findById(id)).thenReturn(Optional.of(category));
        when(converter.convertToDTO(category)).thenReturn(categoryDTO);

        assertEquals(service.getById(id), categoryDTO);

        verify(categoryDAO, only()).findById(id);
        verify(converter, only()).convertToDTO(category);
    }

    @Test
    public void Given_ServiceTriesToFindAllCategories_When_GetNothing_Then_ReturnResultList() {
        List<Category> categories = new ArrayList<>();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categories.add(new Category());
        categories.add(new Category());
        categories.add(new Category());
        categoryDTOS.add(new CategoryDTO());
        categoryDTOS.add(new CategoryDTO());
        categoryDTOS.add(new CategoryDTO());

        when(categoryDAO.findAll()).thenReturn(categories);
        when(converter.convertToDTO(category)).thenReturn(categoryDTO);

        assertEquals(service.getAll(), categoryDTOS);

        verify(categoryDAO, only()).findAll();
        verify(converter, times(3)).convertToDTO(category);
    }

    @Test
    public void Given_ServiceTriesToSaveCategory_When_GetCategoryDTO_Then_ReturnSavedCategory() {
        UserDTO userDTO = new UserDTO();
        userDTO.setConfirmed(true);
        String name = "justName";
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);


        when(userFactory.currentUser()).thenReturn(userDTO);
        when(categoryDAO.existsByName(name)).thenReturn(false);
        when(converter.convertToEntity(categoryDTO)).thenReturn(category);
        when(converter.convertToDTO(category)).thenReturn(categoryDTO);
        when(categoryDAO.save(category)).thenReturn(category);

        assertEquals(service.save(categoryDTO), categoryDTO);

        verify(userFactory, only()).currentUser();
        verify(categoryDAO, times(1)).existsByName(name);
        verify(categoryDAO, times(1)).save(category);
        verify(converter, times(1)).convertToDTO(category);
        verify(converter, times(1)).convertToEntity(categoryDTO);
    }



    @Test
    public void Given_ServiceTriesToSaveCategory_When_CategoryIsNotFound_Then_ThrowsException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setConfirmed(true);
        String name = "justName";
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);

        when(userFactory.currentUser()).thenReturn(userDTO);
        when(categoryDAO.existsByName(name)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> service.save(categoryDTO));

        verify(userFactory, only()).currentUser();
        verify(categoryDAO, only()).existsByName(name);
        verify(categoryDAO, never()).save(category);
        verify(converter, never()).convertToDTO(category);
        verify(converter, never()).convertToEntity(categoryDTO);
    }

    @Test
    public void Given_ServiceTriesToUpdateCategory_When_GetCategory_Then_ReturnUpdatedCategory() {
        Long id = 1L;
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        category.setId(id);

        when(categoryDAO.findById(id)).thenReturn(Optional.of(category));
        when(converter.convertToEntity(categoryDTO)).thenReturn(category);
        when(converter.convertToDTO(category)).thenReturn(categoryDTO);
        when(categoryDAO.update(category)).thenReturn(category);

        assertEquals(service.update(categoryDTO), categoryDTO);

        verify(categoryDAO, times(1)).findById(id);
        verify(categoryDAO, times(1)).update(category);
        verify(converter, times(1)).convertToDTO(category);
        verify(converter, times(1)).convertToEntity(categoryDTO);
    }
    /*
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category categoryFromDB = categoryDAO.findById(categoryDTO.getId()).orElseThrow(() ->
                new NotFoundException()
        );
        if (Objects.equals(categoryDTO.getName(), categoryFromDB.getName())
                && !categoryDTO.getId().equals(categoryFromDB.getId())) {
            throw new AlreadyExistsException("");
        }
        Category category = categoryDAO.update(categoryConverter.convertToEntity(categoryDTO));
        return categoryConverter.convertToDTO(category);
    }
     */

    @Test
    public void Given_ServiceTriesToDeleteCategory_When_NotFound_Then_ThrowException() {
        Long id = 1L;

        when(categoryDAO.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteById(id));

        verify(categoryDAO, only()).findById(id);
        verify(categoryDAO, never()).deleteById(id);
    }
    /*
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
     */

}
