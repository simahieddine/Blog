package com.maison.blog.mappers.Impl;

import com.maison.blog.domain.dtos.Post.CreatePostRequestDto;
import com.maison.blog.domain.dtos.Post.PostDto;
import com.maison.blog.domain.dtos.Post.UpdatePostRequestDto;
import com.maison.blog.domain.entities.CreatePostRequest;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.UpdatePostRequest;
import com.maison.blog.mappers.CategoryMapper;
import com.maison.blog.mappers.PostMapper;
import com.maison.blog.mappers.TagMapper;
import com.maison.blog.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;


    @Override
    public CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto) {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle(dto.getTitle());
        createPostRequest.setContent(dto.getContent());
        createPostRequest.setStatus(dto.getStatus());
        createPostRequest.setTagsIds(dto.getTagsIds());
        createPostRequest.setCategoryId(dto.getCategoryId());
        
        return createPostRequest;
    }

    @Override
    public UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto) {
        UpdatePostRequest updatePostRequest = new UpdatePostRequest();
        updatePostRequest.setId(dto.getId());
        updatePostRequest.setTitle(dto.getTitle());
        updatePostRequest.setContent(dto.getContent());
        updatePostRequest.setStatus(dto.getStatus());
        updatePostRequest.setTagsIds(dto.getTagsIds());
        updatePostRequest.setCategoryId(dto.getCategoryId());
        
        return updatePostRequest;
    }

    @Override
    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setAuthor(userMapper.toDto(post.getAuthor()));
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setStatus(post.getStatus());
        postDto.setCategory(categoryMapper.toDto(post.getCategory()));
        postDto.setTags(post.getTags().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toSet()));
        return postDto;
    }
}
