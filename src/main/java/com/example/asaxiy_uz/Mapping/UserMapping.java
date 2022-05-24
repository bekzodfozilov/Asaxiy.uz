package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.User;
import com.example.asaxiy_uz.Dao.UserDto;

public class UserMapping {
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public static User toEntity(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword()
        );
    }
}
