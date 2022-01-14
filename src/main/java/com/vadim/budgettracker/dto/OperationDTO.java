package com.vadim.budgettracker.dto;

import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class OperationDTO {

    private Long id;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long userId;

    @NotEmpty
    private String categoryName;
}
