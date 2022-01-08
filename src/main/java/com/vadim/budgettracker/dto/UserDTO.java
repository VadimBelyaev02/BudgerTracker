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

  //  @NotBlank
    @Size(min = 5, max = 30)
    private String firstName;

  //  @NotBlank
    @Size(min = 5, max = 30)
    private String lastName;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    @NotBlank
    private String vkId;

    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate createdAt;

    @NotNull
    private Role role;

    @NotNull
    private Boolean confirmed;
}
