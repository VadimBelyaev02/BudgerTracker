package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
