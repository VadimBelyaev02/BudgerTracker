package com.vadim.budgettracker.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OperationDTO {

    private Long id;

    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
  //  @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdDate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long userId;

    private String categoryName;
}
