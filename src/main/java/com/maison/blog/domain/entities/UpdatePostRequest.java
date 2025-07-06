package com.maison.blog.domain.entities;


import com.maison.blog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequest {


    private UUID id;


    private String title;


    private String content;


    private PostStatus status;


    private UUID categoryId;

    @Builder.Default
    private Set<UUID> tagsIds = new HashSet<>();
}
