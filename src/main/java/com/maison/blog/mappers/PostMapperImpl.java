package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.Post.CreatePostRequest;
import com.maison.blog.domain.dtos.Post.PostDto;
import com.maison.blog.domain.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapperImpl implements postMapper {
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;


    @Override
    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setStatus(post.getStatus());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUpdatedAt(post.getUpdatedAt());
        postDto.setAuthor(userMapper.toDto(post.getAuthor()));
        postDto.setCategory(categoryMapper.toDto(post.getCategory()));
        postDto.setTags(post.getTags().stream().map(tagMapper::toDto).collect(Collectors.toSet()));
        postDto.setReadingTime(post.getReadingTime());

        return postDto;
    }

    @Override
    public Post toEntity(CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setStatus(request.getStatus());
        post.setReadingTime(calculateReadingTime(request.getContent()));

        return post;
    }

    private int calculateReadingTime(String content) {
        if (content == null || content.isBlank()){
            return 0;
        }
        String[] words = content.trim().split("\\s+");
        int wordsCount = words.length;
        int readingSpeed = 200; // words per minute
        return Math.max(1, (int) Math.ceil((double) wordsCount / readingSpeed)); // évite 0
    }
}
