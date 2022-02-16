package com.vadim.budgettracker.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {

    private String token;

    private Long userId;
}
