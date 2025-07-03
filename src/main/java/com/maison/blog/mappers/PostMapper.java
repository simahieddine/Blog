package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.Post.CreatePostRequest;
import com.maison.blog.domain.dtos.Post.PostDto;
import com.maison.blog.domain.entities.Post;

public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(CreatePostRequest request);
}
