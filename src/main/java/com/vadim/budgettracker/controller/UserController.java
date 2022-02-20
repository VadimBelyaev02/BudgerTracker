package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User Controller", description = "It allows you to get, update and delete users")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "It allows you to get a user by id in url")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @Operation(description = "It allows you to get all users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @Operation(description = "It allows you to add a new user using the request body")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return userService.save(userDTO);
    }

    @Operation(description = "It allows you to update a user using the request body")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return userService.update(userDTO);
    }

    @Operation(description = "It allows you to delete a user by id in url")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
