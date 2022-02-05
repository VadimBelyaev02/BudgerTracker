package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.model.ChangePasswordRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;
import com.vadim.budgettracker.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Authorization Controller", description = "It is used for authorization users")
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Operation(
            summary = "Authorize method",
            description = "It allows you to authorize a user"
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtToken authorize(@Valid @RequestBody AuthorizationRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors())  {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return authorizationService.authorize(requestDTO);
    }


}
