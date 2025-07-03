package com.maison.blog.services;

import com.maison.blog.domain.entities.Category;
import com.maison.blog.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    //------------------------------------------------------------------------------------

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category not found")
                );

    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPost();
    }


    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(
                        category -> categoryRepository.delete(category),
                        () -> {
                            throw new EntityNotFoundException("Category not found");
                        }
                );

    }
}




