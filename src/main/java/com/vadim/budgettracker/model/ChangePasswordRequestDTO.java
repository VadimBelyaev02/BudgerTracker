package com.vadim.budgettracker.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequestDTO {

    @Size(min = 4, max = 30)
    @NotBlank
    private String oldPassword;


    @Size(min = 4, max = 30)
    @NotBlank
    private String newPassword;
}
