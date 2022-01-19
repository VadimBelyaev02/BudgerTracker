package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.model.RegistrationRequestDTO;

public interface RegistrationService {

    void register(RegistrationRequestDTO registrationDTO);

    void confirm(String code);
}
