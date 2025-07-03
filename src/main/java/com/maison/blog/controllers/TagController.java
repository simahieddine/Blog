package com.maison.blog.controllers;

import com.maison.blog.domain.dtos.Tag.CreateTagRequest;
import com.maison.blog.domain.dtos.Tag.TagDto;
import com.maison.blog.domain.entities.Tag;
import com.maison.blog.mappers.TagMapper;
import com.maison.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    //-----------------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<TagDto>> listTag(){
        return ResponseEntity.ok(
                tagService.listTags().stream()
                        .map(tagMapper::toDto)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody CreateTagRequest request){
        Tag tag = tagMapper.toEntity(request);
        return new ResponseEntity<>(
                tagMapper.toDto(tagService.createTag(tag)),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
