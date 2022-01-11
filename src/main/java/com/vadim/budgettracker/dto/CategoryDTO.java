package com.vadim.budgettracker.dto;

import com.vadim.budgettracker.entity.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Section section;

    @NotBlank
    private String color;

    @NotBlank
    private String logo;

    @NotNull
    private Long userId;
}
