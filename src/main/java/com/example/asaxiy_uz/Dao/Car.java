package com.example.asaxiy_uz.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(generator = "car_id_seq")
    @SequenceGenerator(name = "car_id_seq",sequenceName = "car_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "model")
    private String model;

    @Column(name = "date_of_sanitary_release")
    private String date_of_sanitary_release;

    @Column(name = "bolim_id")
    private Integer bolim_id;

    public Car(Integer id, String name, Integer cost, String model, String date_of_sanitary_release) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.model = model;
        this.date_of_sanitary_release = date_of_sanitary_release;
    }
}
