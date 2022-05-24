package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.Author;
import com.example.asaxiy_uz.Dto.AuthorDto;

public class AuthorMapping {

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getFristname(),
                author.getLastname(),
                author.getBirthdate()
        );
    }

    public static Author toEntity(AuthorDto authorDto) {
        return new Author(
                authorDto.getId(),
                authorDto.getFristname(),
                authorDto.getLastname(),
                authorDto.getBirthdate()
        );
    }
}
