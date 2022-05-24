package com.example.asaxiy_uz.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq",sequenceName = "orders_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "by_id")
    private Integer by_id;

    @Column(name = "user_id")
    private Integer user_id;

    public Order(Integer by_id, Integer user_id) {
        this.by_id = by_id;
        this.user_id = user_id;
    }
}
