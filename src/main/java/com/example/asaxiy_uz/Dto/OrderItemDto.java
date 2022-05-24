package com.example.asaxiy_uz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Integer id;

    private Integer orders_id;

    private Integer zakaz_id;

    private boolean payed;
}
