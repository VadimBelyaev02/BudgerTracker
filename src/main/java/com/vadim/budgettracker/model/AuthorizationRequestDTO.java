package com.vadim.budgettracker.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class AuthorizationRequestDTO {

    @Email
    private String email;

    @Size(min = 5, max = 20)
    private String password;
}
