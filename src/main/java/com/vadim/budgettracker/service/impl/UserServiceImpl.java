package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDTO getByEmail(String email) {
        User user = userDAO.findByEmail(email).orElseThrow(() ->
            new NotFoundException("User is not found")
        );
        return userConverter.convertToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO getById(Long id) {
        User user = userDAO.findById(id).orElseThrow(() ->
            new NotFoundException("User with id=" + id + " is not found")
        );
        return userConverter.convertToDTO(user);
    }

    @Override
    @Transactional
    public List<UserDTO> getAll() {
        return userDAO.findAll().stream()
                .map(userConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public UserDTO update(UserDTO userDTO) {
        if (!userDAO.existsByEmail(userDTO.getEmail())) {
            throw new NotFoundException("User with email=" + userDTO.getEmail() + " is not found");
        }
        // need to check that a new username doesn't exist in db. and check
        return userConverter.convertToDTO(userDAO.update(userConverter.convertToEntity(userDTO)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userDAO.existsById(id)) {
            throw new NotFoundException("User with id=" + id + " is not found");
        }
        userDAO.deleteById(id);
    }
}
