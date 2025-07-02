package com.maison.blog.services;


import com.maison.blog.domain.entities.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<Tag> listTags();
    Tag createTag(Tag tag);
    Tag updateTag(Tag tag, UUID id);
    void deleteTag(UUID uuid);

}
