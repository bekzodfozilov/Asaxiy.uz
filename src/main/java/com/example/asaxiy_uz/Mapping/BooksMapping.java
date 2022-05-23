package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.Books;
import com.example.asaxiy_uz.Dto.BooksDto;

public class BooksMapping {
    public static Books ToEntitiy(BooksDto booksDto) {
        return new Books(
                booksDto.getId(),
                booksDto.getName(),
                booksDto.getCost(),
                booksDto.getPublisher_date(),
                booksDto.getPage_count(),
                booksDto.getGenre()
        );
    }
}
