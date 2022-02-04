package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AccessDeniedException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;
import com.vadim.budgettracker.security.jwt.JwtTokenProvider;
import com.vadim.budgettracker.service.AuthorizationService;
import com.vadim.budgettracker.service.MailSenderService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JwtTokenProvider tokenProvider;

    public AuthorizationServiceImpl(AuthenticationManager authenticationManager, UserDAO userDAO, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDAO = userDAO;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public JwtToken authorize(AuthorizationRequestDTO requestDTO) {
        User user = userDAO.findByEmail(requestDTO.getEmail()).orElseThrow(() ->
                new NotFoundException("User with email = " + requestDTO.getEmail() + " is not found")
        );
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestDTO.getEmail(), requestDTO.getPassword()));

            Long userId = user.getId();
            String token = tokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
            return new JwtToken(token, userId);
        } catch (AuthenticationException e) {
            throw new AccessDeniedException("Invalid email or password", e);
        }

    }



}
