package com.example.asaxiy_uz.Repository;


import com.example.asaxiy_uz.Dao.Bolim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BolimRepository extends JpaRepository<Bolim,Integer>{
//    @Query(name = "getColumn", value = "select books.id , books.name , books.cost , books.publisher_date, books.page_count, books.genre ," +
//            " a.fristname , a.lastname , a.birthdate from books join author a on a.id = books.author_id",nativeQuery = true)
//    Page getColumn(PageRequest pageRequest);

    }