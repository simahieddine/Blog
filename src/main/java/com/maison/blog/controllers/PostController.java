package com.maison.blog.controllers;

import com.maison.blog.domain.dtos.Post.CreatePostRequest;
import com.maison.blog.domain.dtos.Post.PostDto;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.User;
import com.maison.blog.mappers.PostMapper;
import com.maison.blog.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    //--------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID categoryId,
                                                     @RequestParam(required = false) UUID tagId) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        return ResponseEntity.ok(
                posts.stream()
                        .map(postMapper::toDto)
                        .toList()
        );
    }





}
