package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.Tag.CreateTagRequest;
import com.maison.blog.domain.dtos.Tag.TagDto;
import com.maison.blog.domain.dtos.User.CreateUserRequest;
import com.maison.blog.domain.dtos.User.UserDto;
import com.maison.blog.domain.entities.Tag;
import com.maison.blog.domain.entities.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(CreateUserRequest request);
}
