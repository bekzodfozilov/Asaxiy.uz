package com.example.asaxiy_uz.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(generator = "author_id_seq")
    @SequenceGenerator(name = "author_id_seq",sequenceName = "author_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "fristname")
    private String fristname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private String birthdate;
}
