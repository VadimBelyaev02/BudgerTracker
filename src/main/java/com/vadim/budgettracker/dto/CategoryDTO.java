package com.vadim.budgettracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String section;

    @NotBlank
    private String color;

    @NotBlank
    private String logo;

    @NotNull
    private Long userId;
}
