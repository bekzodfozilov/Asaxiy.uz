package com.example.asaxiy_uz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Integer id;

    private String name;

    private Integer cost;

    private String date_of_sanitary_release;

    private String model;

    private Integer bolim_id;
}
