package com.example.asaxiy_uz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {

    private Integer id;

    private String name;

    private Integer cost;

    private String publisher_date;

    private Integer page_count;

    private String genre;

    private Integer author_id;

    private Integer bolim_id;

}
