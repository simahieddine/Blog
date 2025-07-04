package com.maison.blog.services;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.dtos.Post.CreatePostRequest;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.Tag;
import com.maison.blog.domain.entities.User;
import com.maison.blog.mappers.PostMapper;
import com.maison.blog.repositories.PostRepository;
import com.maison.blog.repositories.TagRepository;
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
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with ID " + id));
    }


    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest request){
        if (user == null || request == null){
            throw new IllegalArgumentException("User or Post cannot be null");
        }

        Post postToSave = postMapper.toEntity(request);
        postToSave.setAuthor(user);

        Category category = categoryService.getCategoryById(request.getCategoryId());
        postToSave.setCategory(category);

        Set<Tag> tags = new HashSet<>(tagRepository.findAllById(request.getTagsIds()));
        postToSave.setTags(tags);

        if(tags.size() != request.getTagsIds().size()){
            throw new EntityNotFoundException("One or more tags do not exist in the database.");
        }

        return postRepository.save(postToSave);
    }


    @Override
    @Transactional
    public void deletePost(UUID id){
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (!postRepository.existsById(id)){
            throw new EntityNotFoundException("Post not found with Id: " + id);
        }

        postRepository.deleteById(id);

    }




}
