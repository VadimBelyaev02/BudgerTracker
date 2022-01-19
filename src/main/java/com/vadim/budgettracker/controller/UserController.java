package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.enums.Role;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public UserDTO getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO putUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
