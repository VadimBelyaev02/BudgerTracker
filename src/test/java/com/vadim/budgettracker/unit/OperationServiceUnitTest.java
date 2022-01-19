package com.vadim.budgettracker.unit;

import com.vadim.budgettracker.dao.OperationRepository;
import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.dto.converter.OperationConverter;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.exception.AlreadyExistsException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserService test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class OperationServiceUnitTest {

    @InjectMocks
    private OperationServiceImpl service;

    @Mock
    private OperationRepository repository;

    @Mock
    private OperationConverter converter;

    private final Long id = 1L;

    @Test
    public void Given_ServiceTriesToFindOperation_When_GetOperationId_Then_FoundOperationIsReturned() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();

        when(repository.findById(id)).thenReturn(Optional.of(operation));
        when(converter.convertToDTO(operation)).thenReturn(operationDTO);

        assertEquals(service.getById(id), operationDTO);

        verify(repository, only()).findById(id);
        verify(converter, only()).convertToDTO(operation);
    }

    @Test
    public void Given_OperationId_When_TryToFindById_Then_ThrowNotFoundException() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(id));

        verify(repository, only()).findById(id);
        verify(converter, never()).convertToDTO(operation);
    }

    @Test
    public void Given_ServiceTriesToFindAllOperations_When_GetNothing_Then_ResultIsReturned() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();
        List<Operation> operations = new ArrayList<>();
        List<OperationDTO> operationDTOS = new ArrayList<>();
        operations.add(operation);
        operations.add(operation);
        operations.add(operation);

        operationDTOS.add(operationDTO);
        operationDTOS.add(operationDTO);
        operationDTOS.add(operationDTO);

        when(converter.convertToDTO(operation)).thenReturn(operationDTO);
        when(repository.findAll()).thenReturn(operations);

        assertEquals(service.getAll(), operationDTOS);

        verify(converter, times(3)).convertToDTO(operation);
        verify(repository, only()).findAll();
    }

    @Test
    public void Given_OperationDTO_When_TryToSave_Then_SavedDTOIsReturned() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(id);

        when(repository.existsById(id)).thenReturn(false);
        when(repository.save(operation)).thenReturn(operation);
        when(converter.convertToDTO(operation)).thenReturn(operationDTO);
        when(converter.convertToEntity(operationDTO)).thenReturn(operation);

        assertEquals(service.save(operationDTO), operationDTO);

        verify(converter, times(1)).convertToDTO(operation);
        verify(converter, times(1)).convertToEntity(operationDTO);
        verify(repository, times(1)).save(operation);
        verify(repository, times(1)).existsById(id);
    }

    @Test
    public void Given_OperationDTO_When_TryToSave_Then_ThrowAlreadyExistsException() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(id);

        when(repository.existsById(id)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> service.save(operationDTO));

        verify(converter, never()).convertToDTO(operation);
        verify(converter, never()).convertToEntity(operationDTO);
        verify(repository, never()).save(operation);
        verify(repository, times(1)).existsById(id);
    }

    @Test
    public void Given_OperationDTO_When_TryToUpdate_Then_UpdatedDTOIsReturned() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(id);

        when(repository.existsById(id)).thenReturn(true);
        when(converter.convertToDTO(operation)).thenReturn(operationDTO);
        when(converter.convertToEntity(operationDTO)).thenReturn(operation);
        when(repository.save(operation)).thenReturn(operation);

        assertEquals(service.update(operationDTO), operationDTO);

        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(operation);
        verify(converter, times(1)).convertToEntity(operationDTO);
        verify(converter, times(1)).convertToDTO(operation);
    }

    @Test
    public void Given_OperationDTO_When_TryToUpdate_Then_ThrowNotFoundException() {
        Operation operation = new Operation();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(id);

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.update(operationDTO));

        verify(repository, only()).existsById(id);
        verify(converter, never()).convertToDTO(operation);
        verify(converter, never()).convertToEntity(operationDTO);
        verify(repository, never()).save(operation);
    }

    @Test
    public void Given_OperationId_When_TryToDeleteById_Then_ThrowNotFoundException() {
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.deleteById(id));

        verify(repository, only()).existsById(id);
        verify(repository, never()).deleteById(id);
    }
}
