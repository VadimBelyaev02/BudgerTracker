package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;
import com.vadim.budgettracker.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public JwtToken authorize(@Valid AuthorizationRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors())  {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return authorizationService.authorize(requestDTO);
    }

}
