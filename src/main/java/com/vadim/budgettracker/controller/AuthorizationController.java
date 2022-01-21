package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;
import com.vadim.budgettracker.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtToken authorize(@Valid AuthorizationRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors())  {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return authorizationService.authorize(requestDTO);
    }
}
