package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.OperationDAO;
import com.vadim.budgettracker.dao.OperationRepository;
import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.dto.converter.OperationConverter;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationConverter operationConverter;

    public OperationServiceImpl(OperationRepository operationRepository, OperationConverter operationConverter) {
        this.operationRepository = operationRepository;
        this.operationConverter = operationConverter;
    }

    @Override
    public OperationDTO getById(Long id) {
        Operation operation = operationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Operation with id=" + id + " is not found")
        );
        return operationConverter.convertToDTO(operation);
    }

    @Override
    public List<OperationDTO> getAll() {
        return operationRepository.findAll().stream()
                .map(operationConverter::convertToDTO)
                .collect(Collectors.toList());
//        return operationDAO.findAll().stream()
//                .map(operationConverter::convertToDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO save(OperationDTO operationDTO) {
        if (operationRepository.existsById(operationDTO.getId())) {
            throw new AlreadyExistsException("Operation with id=" + operationDTO.getId() + " already exists");
        }
        // need to fix the bug that id is not null, but has to be
        Operation operation = operationRepository.save(operationConverter.convertToEntity(operationDTO));
        return operationConverter.convertToDTO(operation);
    }

    @Override
    public OperationDTO update(OperationDTO operationDTO) {
        if (!operationRepository.existsById(operationDTO.getId())) {
            throw new NotFoundException("Operation with id=" + operationDTO.getId() + " is not found");
        }
        Operation operation = operationRepository.save(operationConverter.convertToEntity(operationDTO));
        return operationConverter.convertToDTO(operation);
    }

    @Override
    public void deleteById(Long id) {
        if (!operationRepository.existsById(id)) {
            throw new NotFoundException("Operation with id=" + id + " is not found");
        }
        operationRepository.deleteById(id);
    }

    @Override
    public List<OperationDTO> getAllByUserId(Long userId) {
        return operationRepository.findAllByUserId(userId).stream()
                .map(operationConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO getUserOperationById(Long userId, Long operationId) {
//        Operation operation = operationRepository.findByOperationIdAndUserId(operationId, userId)
//                .orElseThrow(() -> {
//                    throw new NotFoundException("Operation with userId=" + userId + " and" +
//                            " operationId=" + operationId + " is not found");
//                });
//        return operationConverter.convertToDTO(operation);
//        }
        return null;
    }
}
