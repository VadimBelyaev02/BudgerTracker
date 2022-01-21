package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;
import com.vadim.budgettracker.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegistrationRequestDTO registrationDTO) {
        registrationService.register(registrationDTO);
    }

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@RequestParam String code) {
        registrationService.confirm(code);
    }

    @PostMapping("/forgot_password")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestBody String email) {
        registrationService.resetPassword(email);
    }

    @PutMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        registrationService.updatePassword(requestDTO);
    }
}
