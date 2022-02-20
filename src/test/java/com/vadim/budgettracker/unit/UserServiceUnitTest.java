package com.vadim.budgettracker.unit;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.UserService;
import com.vadim.budgettracker.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("UserService test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserDAO userDAO;

    @Test
    public void Given_ServiceTriesToFindUser_When_GetUserId_Then_FoundUserIsReturned() {
        Long id = 1L;
        String email = "vadimbelaev002@gmail.com";
        User user = new User();
        UserDTO userDTO = new UserDTO();
        user.setId(id);
        user.setEmail(email);

        when(userDAO.findById(id)).thenReturn(Optional.of(user));

        when(userConverter.convertToDTO(user)).thenReturn(userDTO);

        assertEquals(userService.getById(id), userDTO);

        verify(userDAO, only()).findById(id);
        verify(userConverter, only()).convertToDTO(user);
    }

    @Test
    public void Given_ServiceTriesToFindAllUsers_When_GetNoOneField_Then_FoundUsersIsReturned() {
        List<User> users = new ArrayList<>();
        List<UserDTO> userDTOS = new ArrayList<>();
        User user = new User();
        UserDTO userDTO = new UserDTO();
        users.add(user);
        users.add(user);

        userDTOS.add(userDTO);
        userDTOS.add(userDTO);

        when(userDAO.findAll()).thenReturn(users);
        when(userConverter.convertToDTO(user)).thenReturn(userDTO);

        assertEquals(userService.getAll(), userDTOS);

        verify(userDAO, only()).findAll();
        verify(userConverter, Mockito.times(2)).convertToDTO(user);
    }

    @Test
    public void Given_ServiceTriesToFindUserByEmail_When_GetUser_Then_FoundUserIsReturned() {
        String email = "vadimbealev002@gmail.com";
        User user = new User();
        UserDTO userDTO = new UserDTO();
        user.setEmail(email);
        userDTO.setEmail(email);

        when(userDAO.findByEmail(email)).thenReturn(Optional.of(user));
        when(userConverter.convertToDTO(user)).thenReturn(userDTO);

        assertEquals(userService.getByEmail(email), userDTO);

        verify(userDAO, only()).findByEmail(email);
        verify(userConverter, only()).convertToDTO(user);
    }

    @Test
    public void Given_ServiceTriesToFindUserById_When_UserIsNotFound_Then_ThrowException() {
        User user = new User();
        UserDTO userDTO = new UserDTO();
        Long id = 2L;
        userDTO.setId(id);

        when(userDAO.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(id));

        verify(userDAO, only()).findById(id);
        verify(userConverter, Mockito.never()).convertToDTO(user);
    }

    @Test
    public void Given_ServiceTriesToUpdateUser_When_UserIsNotFound_Then_ThrowException() {
        String email = "vadimbelaev002@gmail.com";
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        User user = new User();

        when(userDAO.findByEmail(email)).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> userService.update(userDTO));

        verify(userDAO, only()).findByEmail(email);
        verify(userConverter, never()).convertToDTO(user);
    }

    @Test
    public void Given_ServiceTriesToDeleteUserById_When_UserIsNotFound_Then_ThrowException() {
        Long id = 1L;

        Mockito.when(userDAO.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> userService.deleteById(id));

        verify(userDAO, only()).existsById(id);
        verify(userDAO, Mockito.never()).deleteById(id);
    }

    @Test
    public void Given_ServiceTriesUpdateUser_When_GetUser_Then_UpdatedUserIsReturned() {
        String email = "vadimbelaev002@gmail.com";
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);

        when(userDAO.findByEmail(email)).thenReturn(Optional.of(user));
        when(userConverter.convertToDTO(user)).thenReturn(userDTO);
        when(userConverter.convertToEntity(userDTO)).thenReturn(user);
        when(userDAO.update(user)).thenReturn(user);

        assertEquals(userService.update(userDTO), userDTO);

        verify(userDAO, Mockito.times(1)).findByEmail(email);
        verify(userConverter, Mockito.times(1)).convertToDTO(user);
        verify(userDAO, Mockito.times(1)).update(user);
        verify(userConverter, Mockito.times(1)).convertToEntity(userDTO);
    }
}
