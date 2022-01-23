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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Get a category",
            description = "It allows you to get a category by id in url"
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable("id") Long id) {
        return new CategoryDTO();
       // return categoryService.getById(id);
    }

    @Operation(
            summary = "Get all categories",
            description = "It allows you to get all categories"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll();
    }

    @Operation(
            summary = "Create a category",
            description = "It allows you to add a new category by request body"
    )
    @PostMapping
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
    @PutMapping
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
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }
}
