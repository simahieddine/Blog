package com.maison.blog.services;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.Tag;
import com.maison.blog.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;


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


}
