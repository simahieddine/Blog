package com.maison.blog.services.Impl;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.entities.*;
import com.maison.blog.domain.entities.CreatePostRequest;
import com.maison.blog.mappers.PostMapper;
import com.maison.blog.repositories.PostRepository;
import com.maison.blog.repositories.TagRepository;
import com.maison.blog.services.CategoryService;
import com.maison.blog.services.PostService;
import com.maison.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;


    //-------------------------------------------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {

        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContains(
                    PostStatus.PUBLISHED,
                    category,
                    tag);

        } else if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);

        } else if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContains(PostStatus.PUBLISHED, tag);

        } else {
            return postRepository.findAllByStatus(PostStatus.PUBLISHED);
        }
    }





    @Override
    public Post getPost(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Post post = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Post not found with ID: " + id)
        );

        return post;
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        if (user == null){ throw new IllegalArgumentException("User cannot be null"); }
        if (createPostRequest == null){ throw new IllegalArgumentException("Request cannot be null"); }

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());

        Set<Tag> tags = new HashSet<>();
        for (UUID tagId : createPostRequest.getTagsIds()){
            tags.add(tagService.getTagById(tagId));
        }

        Post postToSave = new Post();
        postToSave.setTitle(createPostRequest.getTitle());
        postToSave.setContent(createPostRequest.getContent());
        postToSave.setStatus(createPostRequest.getStatus());
        postToSave.setReadingTime(calculateReadingTime(createPostRequest.getContent()));
        postToSave.setCategory(category);
        postToSave.setTags(tags);
        postToSave.setAuthor(user);

        return postRepository.save(postToSave);

    }





    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        if (updatePostRequest == null || id == null){
            throw new IllegalArgumentException("Post and Id are required");
        }
        if (!id.equals(updatePostRequest.getId())){
            throw new IllegalArgumentException("Id in path and body must be the same");
        }
        Post existingPost = getPost(id);
        existingPost.setTitle(updatePostRequest.getTitle());
        existingPost.setContent(updatePostRequest.getContent());
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(updatePostRequest.getContent()));

        Category category = categoryService.getCategoryById(updatePostRequest.getCategoryId());
        existingPost.setCategory(category);

        Set<Tag> tags = new HashSet<>();
        for (UUID tagId : updatePostRequest.getTagsIds()){
            tags.add(tagService.getTagById(tagId));
        }

        existingPost.setTags(tags);

        return postRepository.save(existingPost);
    }





    @Override
    public void deletePost(UUID id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        postRepository.findById(id).ifPresentOrElse(
                (post) -> postRepository.delete(post),
                () -> {throw new EntityNotFoundException("Post not found with ID: " + id);}
        );
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
