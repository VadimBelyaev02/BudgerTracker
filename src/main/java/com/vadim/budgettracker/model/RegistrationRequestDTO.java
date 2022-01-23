package com.vadim.budgettracker.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequestDTO {

    @Size(min = 1, max = 30)
    @NotBlank
    private String nickname;

    @Email
    private String email;

    @Size(min = 4, max = 15)
    @NotBlank
    private String password;

}
