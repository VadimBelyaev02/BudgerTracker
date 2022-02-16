package com.vadim.budgettracker.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtToken {

    private String token;

    private Long userId;
}
