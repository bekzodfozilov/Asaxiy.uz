package com.example.asaxiy_uz.Dao;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq",sequenceName = "order_item_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "orders_id")
    private Integer orders_id;

    @Column(name = "zakaz_id")
    private Integer zakaz_id;

    @Column(name = "payed")
    private boolean payed;

    public OrderItem(Integer orders_id, Integer zakaz_id) {
        this.orders_id = orders_id;
        this.zakaz_id = zakaz_id;
    }
}
