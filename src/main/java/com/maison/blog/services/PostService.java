package com.maison.blog.services;

import com.maison.blog.domain.entities.CreatePostRequest;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.UpdatePostRequest;
import com.maison.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    Post getPost(UUID id);

    Post createPost(User user, CreatePostRequest createPostRequest);

    void deletePost(UUID id);

    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}
