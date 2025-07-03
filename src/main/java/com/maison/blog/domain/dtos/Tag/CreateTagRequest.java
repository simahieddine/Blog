package com.maison.blog.domain.dtos.Tag;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest {

    @NotBlank(message = "Tag name is required")
    @Size(min = 2, max = 20, message = "Tag name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Tag name can only contain letters A-Z, numbers, and spaces")
    private String name;
}
