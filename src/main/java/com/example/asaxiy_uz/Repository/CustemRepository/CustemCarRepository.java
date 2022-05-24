package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyCar;
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
public class CustemCarRepository {

    private final EntityManager entityManager;

    private Integer page;

    private Integer size;

    public Optional<?> getCar(MultiValueMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1");

        if (IntegerHelper.Isvalid(params.getFirst("size")) && IntegerHelper.Isvalid(params.getFirst("page"))) {
            stringBuilder.append(" limit :size offset :page");
        }

        queryParams(stringBuilder, params);

        String s = "select c.id, bolim.name as bolim, c.name, c.cost," +
                " c.model , c.date_of_sanitary_release from bolim join car c on bolim.id = c.bolim_id " + stringBuilder;

        Query query = entityManager.createNativeQuery(s, EntitiyCar.class);

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
            stringBuilder.append(" and c.id = :id");
        }
        if (StringHelper.isValid(params.getFirst("name"))) {
            stringBuilder.append(" and c.name = : name");
        }
        if (IntegerHelper.Isvalid(params.getFirst("cost"))) {
            stringBuilder.append(" and c.cost = :cost");
        }
        if (StringHelper.isValid(params.getFirst("date_of_sanitary_release"))) {
            stringBuilder.append(" c.and date_of_sanitary_release = :date_of_sanitary_release");
        }
        if (StringHelper.isValid(params.getFirst("model"))) {
            stringBuilder.append(" and c.model = :model");
        }

    }

    private void quveryValue(Query query, MultiValueMap<String, String> params) {

        if (IntegerHelper.Isvalid(params.getFirst("id"))) {
            query.setParameter("id", IntegerHelper.isInt(params.getFirst("id")));
        }
        if (StringHelper.isValid(params.getFirst("name"))) {
            query.setParameter("name", params.getFirst("name"));
        }
        if (IntegerHelper.Isvalid(params.getFirst("cost"))) {
            query.setParameter("cost", IntegerHelper.isInt(params.getFirst("cost")));
        }
        if (StringHelper.isValid(params.getFirst("date_of_sanitary_release"))) {
            query.setParameter("date_of_sanitary_release", params.getFirst("date_of_sanitary_release"));
        }
        if (StringHelper.isValid(params.getFirst("model"))) {
            query.setParameter("model", params.getFirst("model"));
        }
    }

    public List getId(Integer id) {

        String s = "select c.id, bolim.name as bolim, c.name, c.cost,\n" +
                "                 c.model , c.date_of_sanitary_release from bolim join car c on bolim.id = c.bolim_id and bolim.id = :id";

        Query query = entityManager.createNativeQuery(s, EntitiyCar.class);

        query.setParameter("id", id);

        return query.getResultList();
    }
}
