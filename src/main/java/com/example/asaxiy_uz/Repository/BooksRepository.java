package com.example.asaxiy_uz.Repository;

import com.example.asaxiy_uz.Dao.Books;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends JpaRepository<Books,Integer> {
        @Query(name = "getColumn", value = "select books.id , books.name , books.cost , books.publisher_date, books.page_count, books.genre ," +
            " a.fristname , a.lastname , a.birthdate from books join author a on a.id = books.author_id and books.id = :id",nativeQuery = true)
    EntitiyBooks getId(@Param(value = "id") int id);
}
