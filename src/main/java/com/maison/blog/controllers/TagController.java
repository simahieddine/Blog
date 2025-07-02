package com.maison.blog.controllers;

import com.maison.blog.domain.dtos.TagDto;
import com.maison.blog.mappers.TagMapper;
import com.maison.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
