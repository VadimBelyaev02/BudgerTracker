package com.vadim.budgettracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
