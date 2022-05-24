package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Author;
import com.example.asaxiy_uz.Dto.AuthorDto;
import com.example.asaxiy_uz.Helper.IntegerHelper;
import com.example.asaxiy_uz.Helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemAuthorRepository {


    private final EntityManager entityManager;

    private Integer page;

    private Integer size;


    public Optional<?> getAuthorParams(MultiValueMap<String, String> params) {

        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1");

        if (IntegerHelper.Isvalid(params.getFirst("size")) && IntegerHelper.Isvalid(params.getFirst("page"))) {
            stringBuilder.append(" limit :size offset :page");
        }

        queryParams(stringBuilder, params);

        String s = "Select * from author " + stringBuilder;

        Query query = entityManager.createNativeQuery(s, Author.class);

        if (IntegerHelper.Isvalid(params.getFirst("size"))) {
            query.setParameter("size", IntegerHelper.isInt(params.getFirst("size")));
        }
        if (IntegerHelper.Isvalid(params.getFirst("page"))) {
            page = IntegerHelper.isInt(params.getFirst("page"));
            size = IntegerHelper.isInt(params.getFirst("size"));
            query.setParameter("page", page * size);
        }

        quveryValue(query, params);


        if (params.containsKey("size") && params.containsKey("page")) {
            if (IntegerHelper.Isvalid(params.getFirst("size")) && IntegerHelper.Isvalid(params.getFirst("page"))) {
                return Optional.of(
                        new PageImpl<>(query.getResultList(), PageRequest.of(page, size), query.getResultList().size())
                );
            }
        }
        return Optional.of(query.getResultList());
    }


    private void queryParams(StringBuilder stringBuilder, MultiValueMap<String, String> params) {
        if (IntegerHelper.Isvalid(params.getFirst("id"))) {
            stringBuilder.append(" and id = :id");
        }
        if (StringHelper.isValid(params.getFirst("fristname"))) {
            stringBuilder.append(" and fristname = :fristname");
        }
        if (StringHelper.isValid(params.getFirst("lastname"))) {
            stringBuilder.append(" and lastname = :lastname");
        }
        if (StringHelper.isValid(params.getFirst("brithdate"))) {
            stringBuilder.append(" and brithdate = :brithdate");
        }
    }

    private void quveryValue(Query query, MultiValueMap<String, String> params) {
        if (IntegerHelper.Isvalid(params.getFirst("id"))) {
            query.setParameter("id", IntegerHelper.isInt(params.getFirst("id")));
        }
        if (StringHelper.isValid(params.getFirst("fristname"))) {
            query.setParameter("fristname", params.getFirst("fristname"));
        }
        if (StringHelper.isValid(params.getFirst("lastname"))) {
            query.setParameter("lastname", params.getFirst("lastname"));
        }
        if (StringHelper.isValid(params.getFirst("brithdate"))) {
            query.setParameter("brithdate", params.getFirst("brithdate"));
        }
    }

}
