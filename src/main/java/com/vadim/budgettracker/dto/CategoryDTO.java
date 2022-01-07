package com.vadim.budgettracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank
    private String name;
}
