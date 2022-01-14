package com.vadim.budgettracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @Size(min = 5, max = 30)
    private String nickname;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate createdDate;

    @NotNull
    private Role role;

    @NotNull
    private Boolean confirmed;

    @NotEmpty
    private String language;

    @NotEmpty
    private String currency;

    @NotEmpty
    private String mode;
}
