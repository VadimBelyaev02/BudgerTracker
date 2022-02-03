package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.ConfirmationRepository;
import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.Confirmation;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import com.vadim.budgettracker.model.ResetPasswordRequestDTO;
import com.vadim.budgettracker.redis.ConfirmationRedisDAO;
import com.vadim.budgettracker.service.MailSenderService;
import com.vadim.budgettracker.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final MailSenderService senderService;
    private final UserDAO userDAO;
    private final ConfirmationRepository confirmationRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(MailSenderService senderService,
                                   UserDAO userDAO,
                                   ConfirmationRedisDAO redisDAO,
                                   ConfirmationRepository confirmationRepository, UserConverter userConverter,
                                   PasswordEncoder encoder) {
        this.senderService = senderService;
        this.userDAO = userDAO;
        this.confirmationRepository = confirmationRepository;
        this.userConverter = userConverter;
        this.encoder = encoder;
    }


    @Override
    @Transactional
    public void register(RegistrationRequestDTO requestDTO) {
        User user = userConverter.convertToEntity(requestDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userDAO.existsByEmailOrNickname(user.getEmail(), user.getNickname())) {
            throw new AlreadyExistsException("User with email =" + user.getEmail() + " and nickname = " + user.getNickname());
        }
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User with email = " + requestDTO.getEmail() + " already exists");
        }
        if (userDAO.existByNickname(user.getNickname())) {
            throw new AlreadyExistsException("User with nickname = " + requestDTO.getNickname() + " already exists");
        }
        String code = UUID.randomUUID().toString();
        String subject = user.getNickname() + ", confirm your profile, please";

        senderService.sendButton(subject, user.getEmail(), code);

        //   redisDAO.save(user.getEmail(), code); <- it doesn't work with heroku for some reason,
        //                                            (it's not my fault!),
        //                                            so I have to use a regular database,
        //                                            but locally it works perfect!
//        confirmationRepository.save(user.getEmail(), code);
        Confirmation confirmation = Confirmation.builder()
                .code(code)
                .id(user.getId())
                .user(user)
                .build();
        user.setConfirmation(confirmation);
        userDAO.save(user);

    }

    @Override
    @Transactional
    public void confirm(String code) {
        Confirmation confirmation = confirmationRepository.findByCode(code)
                .orElseThrow(() ->
                    new NotFoundException("Confirmation with code=" + code + " is not found")
                );
        confirmation.getUser().setConfirmed(true);
        confirmationRepository.deleteByCode(code);
    }

    @Override
    @Transactional
    public void resetPassword(String email) {
        if (!userDAO.existsByEmail(email)) {
            throw new NotFoundException("User with email = " + email + " is not found");
        }
        String subject = "Reset password";
        String code = UUID.randomUUID().toString();
        String message = "Here is your code: " + code;
        senderService.sendText(subject, email, message);
    }

    @Override
    @Transactional
    public void updatePassword(ResetPasswordRequestDTO requestDTO) {
        Confirmation confirmation = confirmationRepository.findByCode(requestDTO.getCode())
                .orElseThrow(() -> (
                        new NotFoundException("Confirmation with code=" + requestDTO.getCode() + " is not found")
                        )
                );
        confirmation.getUser().setPassword(encoder.encode(requestDTO.getNewPassword()));
    }
}

/*
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final MailSenderService senderService;
    private final UserDAO userDAO;
//    private final ConfirmationRedisDAO redisDAO;
    private final UserConverter userConverter;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(MailSenderService senderService,
                                   UserDAO userDAO,
                                   ConfirmationRedisDAO redisDAO,
                                   UserConverter userConverter,
                                   PasswordEncoder encoder) {
        this.senderService = senderService;
        this.userDAO = userDAO;
//        this.redisDAO = redisDAO;
        this.userConverter = userConverter;
        this.encoder = encoder;
    }


    @Override
    @Transactional
    public void register(RegistrationRequestDTO requestDTO) {
        User user = userConverter.convertToEntity(requestDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User with email=" + requestDTO.getEmail() + " already exists");
        }
        String code = UUID.randomUUID().toString();
        String subject = user.getNickname() + ", confirm your profile, please";

        senderService.sendMessage(subject, user.getEmail(), code);
        //   redisDAO.save(user.getEmail(), code); <- it doesn't work with heroku for some reason,
        //                                            (it's not my fault!),
        //                                            so I have to use a regular database,
        //                                            but locally it works perfect!
        redisDAO.save(user.getEmail(), code);
        userDAO.save(user);
        int a;
    }

    @Override
    @Transactional
    public void confirm(String code) {
        String email = redisDAO.findEmailByCode(code);
        User user = userDAO.findByEmail(email).orElseThrow(() ->
            new NotFoundException("User is not found")
        );
        user.setConfirmed(true);
        redisDAO.delete(code);
    }

    @Override
    @Transactional
    public void resetPassword(String email) {
        if (!userDAO.existsByEmail(email)) {
            throw new NotFoundException("User with email = " + email + " is not found");
        }
        String subject = "Reset password";
        String code = UUID.randomUUID().toString();
        senderService.sendMessage(subject, email, code);
    }

    @Override
    @Transactional
    public void updatePassword(ResetPasswordRequestDTO requestDTO) {
        String email = redisDAO.findEmailByCode(requestDTO.getCode());
        User user = userDAO.findByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email=" + email + " is not found")
        );
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
 */
