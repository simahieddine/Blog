package com.maison.blog.controllers;

import com.maison.blog.domain.dtos.Category.CategoryDto;
import com.maison.blog.domain.dtos.Category.CreateCategoryRequest;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.mappers.CategoryMapper;
import com.maison.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<Category> categories = categoryService.listCategories();
        return ResponseEntity.ok(
                categories.stream()
                        .map(categoryMapper::toDto)
                        .toList()
        );

    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        Category categoryToSave = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToSave);
        return  new ResponseEntity<>(
                categoryMapper.toDto(savedCategory),
                HttpStatus.CREATED
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
