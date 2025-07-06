package com.maison.blog.mappers;


import com.maison.blog.domain.dtos.Post.CreatePostRequestDto;
import com.maison.blog.domain.dtos.Post.PostDto;
import com.maison.blog.domain.dtos.Post.UpdatePostRequestDto;
import com.maison.blog.domain.entities.CreatePostRequest;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.UpdatePostRequest;

public interface PostMapper {

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
    PostDto toDto (Post post);


}
