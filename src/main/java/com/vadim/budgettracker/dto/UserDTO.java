package com.vadim.budgettracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.vadim.budgettracker.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @Size(min = 1, max = 30)
    @NotBlank
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

    @NotBlank
    private String language;

    @NotBlank
    private String currency;

    @NotBlank
    private String mode;
}
