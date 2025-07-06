package com.maison.blog.mappers.Impl;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.dtos.Category.CategoryDto;
import com.maison.blog.domain.dtos.Category.CreateCategoryRequest;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.mappers.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.postCount( calculatePostCount( category.getPosts() ) );
        categoryDto.id( category.getId() );
        categoryDto.name( category.getName() );

        return categoryDto.build();
    }


    @Override
    public Category toEntity(CreateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( request.getName() );

        return category.build();
    }

    long calculatePostCount(List<Post> posts) {
        if (posts == null) {
            return 0;
        }

        // Use a HashSet to remove duplicate Post objects
        Set<Post> uniquePosts = new HashSet<>(posts);

        // Filter to count only published posts
        return uniquePosts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
