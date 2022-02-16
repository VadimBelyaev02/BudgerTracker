package com.vadim.budgettracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResetPasswordRequestDTO {

    @NotBlank
    private String code;

    @NotBlank
    @Size(min = 4, max = 15)
    private String newPassword;
}
