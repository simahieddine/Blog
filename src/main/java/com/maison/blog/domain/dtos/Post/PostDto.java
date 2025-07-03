package com.maison.blog.domain.dtos.Post;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.dtos.Category.CategoryDto;
import com.maison.blog.domain.dtos.Tag.TagDto;
import com.maison.blog.domain.dtos.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private PostStatus status;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto author;
    private CategoryDto category;
    private Set<TagDto> tags;
}
