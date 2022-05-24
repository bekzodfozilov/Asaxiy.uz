package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Helper.IntegerHelper;
import com.example.asaxiy_uz.Helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemBooksRepository {

    private final EntityManager entityManager;

    private Integer size;

    private Integer page;


    public Optional<?> getAllBooksParams(MultiValueMap<String, String> param) {

        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1 ");

        if (IntegerHelper.Isvalid(param.getFirst("size")) && IntegerHelper.Isvalid(param.getFirst("page"))) {
            stringBuilder.append(" limit :size offset :page");
        }

        queryParams(stringBuilder, param);

        String s = "select  books.id , bolim.name as bolim, books.name , books.cost , books.publisher_date, books.page_count, books.genre ," +
                " a.fristname , a.lastname , a.birthdate from bolim join books on bolim.id = books.bolim_id join author a on a.id = books.author_id" + stringBuilder;

        Query query = entityManager.createNativeQuery(s, EntitiyBooks.class);

        if (IntegerHelper.Isvalid(param.getFirst("size"))) {
            query.setParameter("size", IntegerHelper.isInt(param.getFirst("size")));
        }
        if (IntegerHelper.Isvalid(param.getFirst("page"))) {
            page = IntegerHelper.isInt(param.getFirst("page"));
            size = IntegerHelper.isInt(param.getFirst("size"));
            query.setParameter("page", page * size);
        }

        quveryValue(query, param);


        if (param.containsKey("size") && param.containsKey("page")) {
            if (IntegerHelper.Isvalid(param.getFirst("size")) && IntegerHelper.Isvalid(param.getFirst("page"))) {
                return Optional.of(
                        new PageImpl<>(query.getResultList(), PageRequest.of(page, size), query.getResultList().size())
                );
            }
        }
        return Optional.of(query.getResultList());
    }


    private void queryParams(StringBuilder stringBuilder, MultiValueMap<String, String> param) {
        if (IntegerHelper.Isvalid(param.getFirst("id"))) {
            stringBuilder.append(" and books.id= :id");
        }
        if (StringHelper.isValid(param.getFirst("name"))) {
            stringBuilder.append(" and books.name =: name");
        }
        if (IntegerHelper.Isvalid(param.getFirst("cost"))) {
            stringBuilder.append(" and books.cost =:cost");
        }
        if (StringHelper.isValid(param.getFirst("publisher_date"))) {
            stringBuilder.append(" and books.publisher_date =: publisher_date");
        }
        if (IntegerHelper.Isvalid(param.getFirst("page_count"))) {
            stringBuilder.append(" and books.page_count = : page_count");
        }
        if (StringHelper.isValid(param.getFirst("genre"))) {
            stringBuilder.append(" and books.genre = :genre");
        }
    }

    private void quveryValue(Query query, MultiValueMap<String, String> param) {
        if (IntegerHelper.Isvalid(param.getFirst("id"))) {
            query.setParameter("id", IntegerHelper.isInt(param.getFirst("id")));
        }
        if (StringHelper.isValid(param.getFirst("name"))) {
            query.setParameter("name", param.getFirst("name"));
        }
        if (IntegerHelper.Isvalid(param.getFirst("cost"))) {
            query.setParameter("cost", IntegerHelper.isInt(param.getFirst("cost")));
        }
        if (StringHelper.isValid(param.getFirst("publisher_date"))) {
            query.setParameter("publisher_date", param.getFirst("publisher_date"));
        }
        if (IntegerHelper.Isvalid(param.getFirst("page_count"))) {
            query.setParameter("page_count", IntegerHelper.isInt(param.getFirst("page_count")));
        }
        if (StringHelper.isValid(param.getFirst("genre"))) {
            query.setParameter("genre", param.getFirst("genre"));
        }
    }

    public List<EntitiyBooks> getBooksId(int id) {

        String s = "select  books.id , bolim.name as bolim, books.name , books.cost , books.publisher_date, books.page_count, books.genre ," +
                " a.fristname , a.lastname , a.birthdate from bolim join books on bolim.id = books.bolim_id join author a on a.id = books.author_id and books.id = :id";

        Query query = entityManager.createNativeQuery(s, EntitiyBooks.class);

        query.setParameter("id", id);

        return query.getResultList();
    }


}
