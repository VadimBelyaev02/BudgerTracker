package com.vadim.budgettracker.controller;

import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.exception.NotValidException;
import com.vadim.budgettracker.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return categoryService.save(categoryDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors().toString());
        }
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }
}
