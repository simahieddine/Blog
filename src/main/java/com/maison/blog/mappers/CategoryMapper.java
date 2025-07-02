package com.maison.blog.mappers;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.dtos.CategoryDto;
import com.maison.blog.domain.dtos.CreateCategoryRequest;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;


public interface CategoryMapper {


    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);
}
