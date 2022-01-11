package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class OperationConverter {

    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;

    public OperationConverter(UserDAO userDAO, CategoryDAO categoryDAO) {
        this.userDAO = userDAO;
        this.categoryDAO = categoryDAO;
    }

    public Operation convertToEntity(OperationDTO operationDTO) {
        final Long id = operationDTO.getId();
        final LocalDate createdDate = operationDTO.getCreatedDate();
        final BigDecimal amount = operationDTO.getAmount();
        final User user = userDAO.getById(operationDTO.getUserId());
        final Category category = categoryDAO.getById(operationDTO.getCategoryId());
        return Operation.builder()
                .id(id)
                .createdDate(createdDate)
                .amount(amount)
                .user(user)
                .category(category)
                .build();
    }

    public OperationDTO convertToDTO(Operation operation) {
        final Long id = operation.getId();
        final LocalDate createdDate = operation.getCreatedDate();
        final BigDecimal amount = operation.getAmount();
        final Long userId = operation.getUser().getId();
        final Long categoryId = operation.getCategory().getId();
        return OperationDTO.builder()
                .id(id)
                .createdDate(createdDate)
                .categoryId(categoryId)
                .userId(userId)
                .amount(amount)
                .build();
    }
}

/*

    @NotNull
    private Long categoryId;
 */