package com.vadim.budgettracker.unit;

import com.vadim.budgettracker.dao.OperationRepository;
import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.dto.converter.OperationConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtTokenProvider;
import com.vadim.budgettracker.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserService test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceUnitTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDAO userDAO;

    @Mock
    private JwtTokenProvider tokenProvider;

    public void Given_AuthorizationRequest_When_TryToAuthorizeUser_Then_JwtTokenIsReturned() {
//        AuthorizationRequestDTO requestDTO = new AuthorizationRequestDTO();
//        requestDTO.setEmail("vadimbelaev002@gmail.com");
//        requestDTO.setPassword("password");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                requestDTO.getEmail(), requestDTO.getPassword()
//        );
//
    }

    /*
        public JwtToken authorize(AuthorizationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestDTO.getEmail(), requestDTO.getPassword()));

            User user = userDAO.findByEmail(requestDTO.getEmail()).orElseThrow(() ->
                 new NotFoundException("User is not found")
            );
            if (!user.getConfirmed()) {
                throw new AccessDeniedException("User is not confirmed");
            }

            String token = tokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
            return new JwtToken(token);
        } catch (AuthenticationException e) {
            throw new AccessDeniedException("Invalid email or password", e);
        }
     */
}
