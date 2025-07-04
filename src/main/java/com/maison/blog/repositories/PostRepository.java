package com.maison.blog.repositories;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.entities.Category;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContains(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContains(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);

}
