package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.OrderItem;

public class OrderItemMapping {
    public static OrderItem toEntiity(Integer id, Integer size) {
        return new OrderItem(
                id,
                size
        );
    }
}
