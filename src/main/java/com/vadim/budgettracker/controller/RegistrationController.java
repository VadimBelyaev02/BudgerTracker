package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//java: reactor.core.publisher.Mono.from(Publisher)Mono/invokeStatic
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

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void register() {
//        RegistrationRequestDTO registrationDTO = new RegistrationRequestDTO();
//        registrationDTO.setEmail("vadimbelaev002@gmail.com");
//        registrationDTO.setNickname("vadimgf");
//        registrationDTO.setPassword("password");
//        registrationService.register(registrationDTO);
//    }

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@RequestParam String code) {
        registrationService.confirm(code);
    }
}
