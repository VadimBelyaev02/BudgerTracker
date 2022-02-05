package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.model.ChangePasswordRequestDTO;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;

public interface RegistrationService {

    void register(RegistrationRequestDTO registrationDTO);

    void confirm(String code);

    void resetPassword(String email);

    void updatePassword(ResetPasswordRequestDTO requestDTO);

    void changePassword(ChangePasswordRequestDTO requestDTO);
}
