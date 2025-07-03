package com.maison.blog.domain.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Category name can only contain letters A-Z, numbers, spaces")
    private String name;
}
