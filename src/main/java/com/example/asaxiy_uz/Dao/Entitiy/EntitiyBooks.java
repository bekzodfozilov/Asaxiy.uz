package com.example.asaxiy_uz.Dao.Entitiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class EntitiyBooks {
    @Id
    private Integer id;

    @Column(name = "bolim")
    private String bolim;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "publisher_date")
    private String publisher_date;

    @Column(name = "page_count")
    private Integer page_count;

    @Column(name = "genre")
    private String genre;

    @Column(name = "fristname")
    private String fristname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private String birthdate;
}
