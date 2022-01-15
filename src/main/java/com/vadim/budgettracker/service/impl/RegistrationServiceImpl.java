package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.redis.ConfirmationRedisDAO;
import com.vadim.budgettracker.service.MailSenderService;
import com.vadim.budgettracker.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final MailSenderService senderService;
    private final UserDAO userDAO;
    private final ConfirmationRedisDAO redisDAO;
    private final UserConverter userConverter;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(MailSenderService senderService,
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
    @Transactional
    public void register(RegistrationRequestDTO requestDTO) {
        if (true) {
            String code = UUID.randomUUID().toString();
            senderService.sendMessage(requestDTO.getNickname(), requestDTO.getEmail(), code); //subg email message
            return;
        }

        User user = userConverter.convertToEntity(requestDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User with email=" + requestDTO.getEmail() + " already exists");
        }
        String code = UUID.randomUUID().toString();
        String subject = user.getNickname() + ", confirm your profile, please";

        senderService.sendMessage(subject, user.getEmail(), code); //subg email message
        redisDAO.save(user.getEmail(), code);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void confirm(String code) {
        String email = redisDAO.findEmailByCode(code);
        User user = userDAO.findByEmail(email).orElseThrow(() ->
            new NotFoundException("User is not found")
        );
        user.setConfirmed(true);
        // have to persists user
        redisDAO.delete(code);
    }
}
