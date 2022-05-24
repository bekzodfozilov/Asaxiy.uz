package com.example.asaxiy_uz.Dao.Entitiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class EntitiyPhones {
    @Id
    private Integer id;

    @Column(name = "bolim")
    private String bolim;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "model")
    private String model;

    @Column(name = "date_of_sanitary_release")
    private String date_of_sanitary_release;
}

