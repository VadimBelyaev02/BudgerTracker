package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;
import com.vadim.budgettracker.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Registration controller", description = "It allows you to register users and recover passwords")
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Operation(
            summary = "Register a new user",
            description = "It allows you to register a new user using request body"
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationRequestDTO registrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        registrationService.register(registrationDTO);
    }

    @Operation(
            summary = "Confirm an account",
            description = "It allows you to confirm a user's account using the code from email in request param"
    )
    @PostMapping("/register/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@RequestParam String code) {
        registrationService.confirm(code);
    }

    @Operation(
            summary = "Forgot a password",
            description = "It allows you to send a message on email a code if you forgot you password"
    )
    @PostMapping("/forgot_password")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestParam String email) {
        registrationService.resetPassword(email);
    }

    @Operation(
            summary = "Reset a password",
            description = "It allows you to reset you password by the code from email message"
    )
    @PutMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        registrationService.updatePassword(requestDTO);
    }


}
