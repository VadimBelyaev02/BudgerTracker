package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationDTO getOperation(@PathVariable("id") Long id) {
        return operationService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationDTO> getAllOperations() {
        return operationService.getAll();
    }

//    @GetMapping("/")
//    @ResponseStatus(HttpStatus.OK)
//    public List<OperationDTO> getAllUserOperations(@RequestParam("userId") Long userId) {
//        return operationService.getAllByUserId(userId);
//    }

   // @GetMapping("/{userId}/{operationId}")
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public OperationDTO getUserOperation(@RequestParam("userId") Long userId,
//                                               @RequestParam("operationId") Long operationId) {
//        return operationService.getUserOperationById(userId, operationId);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDTO addOperation(@Valid @RequestBody OperationDTO operationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return operationService.save(operationDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public OperationDTO updateOperation(@Valid @RequestBody OperationDTO operationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return operationService.update(operationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOperation(@PathVariable("id") Long id) {
        operationService.deleteById(id);
    }

}
