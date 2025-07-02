package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.CreateTagRequest;
import com.maison.blog.domain.dtos.TagDto;
import com.maison.blog.domain.entities.Tag;

public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(CreateTagRequest request);
}
