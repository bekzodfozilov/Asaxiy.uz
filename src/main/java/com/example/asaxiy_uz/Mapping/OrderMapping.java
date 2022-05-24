package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.Order;

public class OrderMapping {
    public static Order toOrder(Integer by_id, Integer user_id) {
        return new Order(
                by_id,
                user_id
        );
    }
}
