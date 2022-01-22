package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.ConfirmationRepository;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.Confirmation;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.ConfirmationService;
import org.springframework.transaction.annotation.Transactional;

public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationRepository confirmationRepository;
    private final UserConverter userConverter;

    public ConfirmationServiceImpl(ConfirmationRepository confirmationRepository, UserConverter userConverter) {
        this.confirmationRepository = confirmationRepository;
        this.userConverter = userConverter;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByCode(String code) {
        Confirmation confirmation = confirmationRepository.findByCode(code).orElseThrow(() -> {
            throw new NotFoundException("Code is not found");
        });
        return userConverter.convertToDTO(confirmation.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public String checkCode(String code) {
        return confirmationRepository.findByCode(code).orElseThrow(() -> {
            throw new NotFoundException("The code is not found");
        }).getCode();
    }

    @Override
    public void deleteByCode(String code) {

    }
}
