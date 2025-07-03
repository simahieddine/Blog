package com.maison.blog.mappers;

import com.maison.blog.domain.PostStatus;
import com.maison.blog.domain.dtos.Tag.CreateTagRequest;
import com.maison.blog.domain.dtos.Tag.TagDto;
import com.maison.blog.domain.entities.Post;
import com.maison.blog.domain.entities.Tag;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class TagMapperImpl implements TagMapper{
    @Override
    public TagDto toDto(Tag tag) {
        if (tag == null){
            return null;
        }
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        tagDto.setPostsCount(calculateTagPostsCount(tag.getPosts()));
        return tagDto;
    }

    private Long calculateTagPostsCount(Set<Post> posts){
        return Optional.ofNullable(posts)
                .map(set -> set.stream()
                        .filter(post -> post.getStatus() == PostStatus.PUBLISHED)
                        .count()).orElse(0L);

    }

    @Override
    public Tag toEntity(CreateTagRequest request) {
        if (request == null)
            return null;
        Tag tag = new Tag();
        tag.setName(request.getName());
        return tag;
    }
}
