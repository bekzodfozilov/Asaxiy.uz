package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Dao.Car;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyLaptops;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyPhones;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemBolimRepository {

    private final EntityManager entityManager;

    public Optional<?> getAllColumn(Integer page, Integer size, String column){
        if(column.equals("books")){
            String s = "select  books.id , bolim.name as bolim, books.name , books.cost , books.publisher_date, books.page_count, books.genre ," +
                    " a.fristname , a.lastname , a.birthdate from bolim join books on bolim.id = books.bolim_id join author a on a.id = books.author_id";


            Query query = entityManager.createNativeQuery(s, EntitiyBooks.class);

            return Optional.of(new PageImpl<>(query.getResultList(),PageRequest.of(page,size),query.getResultList().size()));
        }else if(column.equals("car")){
            String s = "select c.id,bolim.name as bolim, c.name, c.cost,c.model,c.date_of_sanitary_release from bolim join car c on bolim.id = c.bolim_id";

            Query query = entityManager.createNativeQuery(s, Car.class);

            return Optional.of(new PageImpl<>(query.getResultList(),PageRequest.of(page,size),query.getResultList().size()));
        }else if(column.equals("laptops")){
              String s = "select l.id,bolim.name as bolim,l.name,l.cost,l.model,l.date_of_sanitary_release from bolim join laptops l on bolim.id = l.bolim_id";

              Query query = entityManager.createNativeQuery(s, EntitiyLaptops.class);

            return Optional.of(new PageImpl<>(query.getResultList(),PageRequest.of(page,size),query.getResultList().size()));

        }else if(column.equals("phones")){
              String s = "select p.id, bolim.name as bolim , p.name, p.cost,p.model,p.date_of_sanitary_release from bolim join phones p on bolim.id = p.bolim_id";

              Query query = entityManager.createNativeQuery(s, EntitiyPhones.class);

            return Optional.of(new PageImpl<>(query.getResultList(),PageRequest.of(page,size),query.getResultList().size()));


        }
        return null;
    }

}
