package com.maison.blog.services;

import com.maison.blog.domain.entities.Tag;
import com.maison.blog.repositories.TagRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    //----------------------------------------------------------

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public Tag createTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        if (tagRepository.existsByNameIgnoreCase(tag.getName())) {
            throw new EntityExistsException("Tag already exists with name: " + tag.getName());
        }
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag updateTag(Tag tag, UUID id) {
        if (tag == null || tag.getName() == null) {
            throw new IllegalArgumentException("Tag or tag name cannot be null");
        }

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Tag tagToUpdate = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with ID: " + id));

        if (!tagToUpdate.getName().equalsIgnoreCase(tag.getName()) &&
        tagRepository.existsByNameIgnoreCase(tag.getName())) {
            throw new EntityExistsException("Another tag already exists with name: " + tag.getName());
        }
        tagToUpdate.setName(tag.getName());
        return tagRepository.save(tagToUpdate);
    }


    @Override
    @Transactional
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresentOrElse(
                tag -> tagRepository.delete(tag),
                () -> {
                    throw new IllegalArgumentException("Tag not found");
                }
        );
    }
}
