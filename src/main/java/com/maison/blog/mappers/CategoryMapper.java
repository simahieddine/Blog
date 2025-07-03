package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.Category.CategoryDto;
import com.maison.blog.domain.dtos.Category.CreateCategoryRequest;
import com.maison.blog.domain.entities.Category;


public interface CategoryMapper {


    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);
}
