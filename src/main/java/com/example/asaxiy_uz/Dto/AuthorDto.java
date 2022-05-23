package com.example.asaxiy_uz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {


    private Integer id;


    private String fristname;


    private String lastname;

    private String birthdate;
}
