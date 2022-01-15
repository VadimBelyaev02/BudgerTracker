package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getByEmail(String email);

    UserDTO getById(Long id);

    List<UserDTO> getAll();

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteById(Long id);

}
