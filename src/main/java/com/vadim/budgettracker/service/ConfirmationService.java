package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dao.ConfirmationRepository;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.dto.converter.UserConverter;
import com.vadim.budgettracker.entity.Confirmation;
import com.vadim.budgettracker.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface ConfirmationService {

    UserDTO getUserByCode(String code);

    String checkCode(String code);

    void deleteByCode(String code);
}
