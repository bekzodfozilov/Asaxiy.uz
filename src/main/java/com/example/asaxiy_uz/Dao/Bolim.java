package com.example.asaxiy_uz.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bolim")
@AllArgsConstructor
@NoArgsConstructor
public class Bolim {

    @Id
    @GeneratedValue(generator = "bolim_id_seq")
    @SequenceGenerator(name = "bolim_id_seq",sequenceName = "bolim_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;
}
