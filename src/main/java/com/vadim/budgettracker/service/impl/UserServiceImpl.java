package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final UserConverter userConverter;

    public UserServiceImpl(UserDAO userDAO, UserConverter userConverter) {
        this.userDAO = userDAO;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = userDAO.findByEmail(email).orElseThrow(() -> {
            throw new NotFoundException("User is not found");
        });
        return userConverter.convertToDTO(user);
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userDAO.findById(id).orElseThrow(() -> {
            throw new NotFoundException(
                    String.format("User with id = %s is not found", id)
            );
        });
        return userConverter.convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userDAO.findAll().stream()
                .map(userConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDAO.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistsException(
                    String.format("User with email = %s already exists", userDTO.getEmail())
            );
        }
        User user = userDAO.save(userConverter.convertToEntity(userDTO));
        return userConverter.convertToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
