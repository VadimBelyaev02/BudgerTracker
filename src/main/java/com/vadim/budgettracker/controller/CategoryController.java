package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Category Controller", description = "It allows you to get, add, update and delete categories")
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Get a category",
            description = "It allows you to get a category by id in url"
    )
    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }

    @Operation(
            summary = "Get all categories",
            description = "It allows you to get all categories"
    )
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll();
    }

    @Operation(
            summary = "Get all user's categories",
            description = "It allows you to get all categories by user's id"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/categories")
    public List<CategoryDTO> getAllUserCategories(@PathVariable("userId") Long userId) {
        return categoryService.getAllByUserId(userId);
    }

    @Operation(
            summary = "Create a category",
            description = "It allows you to add a new category by request body"
    )
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return categoryService.save(categoryDTO);
    }

    @Operation(
            summary = "Update a category",
            description = "It allows you to update a category by request body"
    )
    @PutMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return categoryService.update(categoryDTO);
    }

    @Operation(
            summary = "Delete a category",
            description = "It allows you to delete a category by id in url"
    )
    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }
}
