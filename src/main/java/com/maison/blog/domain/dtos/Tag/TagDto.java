package com.maison.blog.domain.dtos.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private UUID id;
    private String name;
    private Long postsCount;
}
