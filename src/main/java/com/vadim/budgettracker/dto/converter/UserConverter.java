package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Role;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserConverter {

    public User convertToEntity(UserDTO userDTO) {
        final Long id = userDTO.getId();
        final String firstName = userDTO.getFirstName();
        final String lastName = userDTO.getLastName();
        final String email = userDTO.getEmail();
        final String password = userDTO.getPassword();
        final String vkId = userDTO.getVkId();
        final Role role = userDTO.getRole();
        final Boolean confirmed = userDTO.getConfirmed();
        final LocalDate createdAt = userDTO.getCreatedAt();
        return User.builder()
                .id(id)
                .role(role)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .confirmed(confirmed)
                .vkId(vkId)
                .createdAt(createdAt)
                .build();
    }

    public UserDTO convertToDTO(User user) {
        final Long id = user.getId();
        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final Role role = user.getRole();
        final Boolean confirmed = user.getConfirmed();
        final LocalDate createdAt = user.getCreatedAt();
        final String vkId = user.getVkId();
        return UserDTO.builder()
                .id(id)
                .role(role)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .confirmed(confirmed)
                .vkId(vkId)
                .createdAt(createdAt)
                .build();
    }

    public User convertToEntity(RegistrationRequestDTO requestDTO) {
        final String firstName = requestDTO.getFirstName();
        final String lastName = requestDTO.getLastName();
        final String email = requestDTO.getEmail();
        final String password = requestDTO.getPassword();
        final Role role = Role.USER;
        final LocalDate createdAt = LocalDate.now();
        final String vkId = requestDTO.getVkId();
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .confirmed(false)
                .createdAt(createdAt)
                .vkId(vkId)
                .build();
    }
}
