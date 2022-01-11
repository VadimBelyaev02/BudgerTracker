package com.vadim.budgettracker.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequestDTO {

  //  @NotBlank
    @Size(min = 5, max = 30)
    private String nickname;
 //   @NotBlank
    @Email
    private String email;

   // @NotBlank
    @Size(min = 5, max = 15)
    private String password;
}
