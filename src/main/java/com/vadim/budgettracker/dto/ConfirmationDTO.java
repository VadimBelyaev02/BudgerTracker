package com.vadim.budgettracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConfirmationDTO {

    private Long id;

    @NotBlank
    private String code;

    @NotNull
    private Long userId;
}
