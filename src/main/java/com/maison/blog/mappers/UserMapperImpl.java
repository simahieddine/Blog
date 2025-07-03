package com.maison.blog.mappers;

import com.maison.blog.domain.dtos.User.CreateUserRequest;
import com.maison.blog.domain.dtos.User.UserDto;
import com.maison.blog.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserDto toDto(User user) {
        return null;
    }

    @Override
    public User toEntity(CreateUserRequest request) {
        return null;
    }
}
