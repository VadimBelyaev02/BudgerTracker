package com.vadim.budgettracker.service;

import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;

import java.util.Map;

public interface AuthorizationService {

    JwtToken authorize(AuthorizationRequestDTO requestDTO);
}
