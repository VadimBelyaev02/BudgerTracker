package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.enums.Role;
import com.vadim.budgettracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUser(@PathVariable("id") Long id) {
        List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname("fdg");
        userDTO.setCreatedDate(LocalDate.now());
        userDTO.setConfirmed(false);
        userDTO.setRole(Role.USER);

        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        UserDTO userDTO3 = new UserDTO();
        UserDTO userDTO4 = new UserDTO();
        UserDTO userDTO5 = new UserDTO();
        UserDTO userDTO6 = new UserDTO();
        UserDTO userDTO7 = new UserDTO();
        UserDTO userDTO8 = new UserDTO();

        userDTO4.setNickname("IT WORKS!");
        userDTOS.add(userDTO);
        userDTOS.add(userDTO1);
        userDTOS.add(userDTO2);
        userDTOS.add(userDTO3);
        userDTOS.add(userDTO4);
        userDTOS.add(userDTO5);
        userDTOS.add(userDTO6);
        userDTOS.add(userDTO7);
        userDTOS.add(userDTO8);
        return userDTOS;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
