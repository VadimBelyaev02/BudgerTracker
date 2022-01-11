package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.redis.ConfirmationRedisDAO;
import com.vadim.budgettracker.service.RegistrationService;
import com.vadim.budgettracker.service.SenderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final SenderService senderService;
    private final UserDAO userDAO;
    private final ConfirmationRedisDAO redisDAO;
    private final UserConverter userConverter;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(SenderService senderService,
                                   UserDAO userDAO,
                                   ConfirmationRedisDAO redisDAO,
                                   UserConverter userConverter,
                                   PasswordEncoder encoder) {
        this.senderService = senderService;
        this.userDAO = userDAO;
        this.redisDAO = redisDAO;
        this.userConverter = userConverter;
        this.encoder = encoder;
    }


    @Override
    public void register(RegistrationRequestDTO requestDTO) {
        User user = userConverter.convertToEntity(requestDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User already exists");
        }
        String code = UUID.randomUUID().toString();
        String subject = user.getNickname() + ", confirm your profile, please";

        senderService.sendEmail(subject, user.getEmail(), code); //subg email message
     //   senderService.sendVkMessage(user.getVkId(), code);
        userDAO.save(user);
        redisDAO.save(user.getEmail(), code);
    }

    @Override
    @Transactional
    public void confirm(String code) {
        String email = redisDAO.findEmailByCode(code);
        User user = userDAO.findByEmail(email).orElseThrow(() -> {
            throw new NotFoundException("User is not found");
        });
        user.setConfirmed(true);
        redisDAO.delete(code);
    }
}
