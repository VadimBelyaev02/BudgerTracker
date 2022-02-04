package com.vadim.budgettracker.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtToken {

    @JsonProperty("token")
    private String token;

    @JsonProperty("userId")
    private Long userId;
}
