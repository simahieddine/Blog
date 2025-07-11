package com.maison.blog.services;

import com.maison.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category getCategoryById(UUID id);

    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);


}
