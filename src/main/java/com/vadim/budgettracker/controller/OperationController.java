package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Operation Controller", description = "It allows you to get, update, add, delete operations")
@RestController
@RequestMapping("/api")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Operation(
            summary = "Get an operation",
            description = "It allows you to get an operation by id"
    )
    @GetMapping("/operations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationDTO getOperation(@PathVariable("id") Long id) {
        return operationService.getById(id);
    }

    @Operation(summary = "Get all operation",
            description = "It allows you to get all operations"
    )
    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationDTO> getAllOperations() {
         return operationService.getAll();
    }

    @GetMapping("/users/{userId}/operations")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationDTO> getAllUserOperations(@PathVariable("userId") Long userId) {
        return operationService.getAllByUserId(userId);
    }

    @Operation(
            summary = "Create an operation",
            description = "It allows you to add a new operation using the request body"
    )
    @PostMapping("/operations")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDTO addOperation(@Valid @RequestBody OperationDTO operationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return operationService.save(operationDTO);
    }

    @Operation(
            summary = "Update an operation",
            description = "It allows you to update an operation using the request body"
    )
    @PutMapping("/operations")
    @ResponseStatus(HttpStatus.OK)
    public OperationDTO updateOperation(@Valid @RequestBody OperationDTO operationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return operationService.update(operationDTO);
    }

    @Operation(
            summary = "Delete an operation",
            description = "It allows you to delete an operation by id in the url"
    )
    @DeleteMapping("/operations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOperation(@PathVariable("id") Long id) {
        operationService.deleteById(id);
    }
}
