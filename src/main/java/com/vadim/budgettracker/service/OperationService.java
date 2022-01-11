package com.vadim.budgettracker.service;

import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.entity.Operation;

import java.util.List;

public interface OperationService {

    OperationDTO getById(Long id);

    List<OperationDTO> getAll();

    OperationDTO save(OperationDTO operation);

    OperationDTO update(OperationDTO operation);

    void deleteById(Long id);

    List<OperationDTO> getAllByUserId(Long userId);

    OperationDTO getUserOperationById(Long userId, Long operationId);
}
