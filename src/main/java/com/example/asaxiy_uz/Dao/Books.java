package com.example.asaxiy_uz.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(generator = "books_id_seq")
    @SequenceGenerator(name = "books_id_seq",sequenceName = "books_id_seq",allocationSize = 1)
    private Integer id;

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

    @Column(name = "author_id")
    private Integer author_id;

    @Column(name = "bolim_id")
    private Integer bolim_id;

    public Books(Integer id, String name, Integer cost, String publisher_date, Integer page_count, String genre) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.publisher_date = publisher_date;
        this.page_count = page_count;
        this.genre = genre;
    }
}

