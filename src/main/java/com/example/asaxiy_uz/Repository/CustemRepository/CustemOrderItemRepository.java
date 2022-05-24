package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyCar;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyLaptops;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyPhones;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemOrderItemRepository {

    private final EntityManager entityManager;

    public Optional<?> byOrder(Integer zakaz_id, String name) {
        if (name.equals("phones")) {
            String s = "select order_item.id, u.username as bolim , p.name , p.cost , p.date_of_sanitary_release , p.model\n" +
                    "from order_item join orders o on o.id = order_item.orders_id join users u on u.id = o.user_id\n" +
                    " join phones p on o.by_id = p.id\n" + " and order_item.zakaz_id = :id and payed = false";
            Query query = entityManager.createNativeQuery(s, EntitiyPhones.class);
            query.setParameter("id", zakaz_id);
            List<EntitiyPhones> list = query.getResultList();

            return Optional.of(query.getResultList());
        } else if (name.equals("books")) {
            String s = "select order_item.id, u.username as bolim , b.name,b.cost,b.page_count,b.genre,b.publisher_date ,a.lastname,a.fristname,a.birthdate\n" +
                    "from order_item join orders o on o.id = order_item.orders_id join users u on u.id = o.user_id\n" +
                    "join books b on o.by_id = b.id\n" + "join author a on a.id = b.author_id\n" + "and order_item.zakaz_id =:id and payed = false";
            Query query = entityManager.createNativeQuery(s, EntitiyBooks.class);
            query.setParameter("id", zakaz_id);
            return Optional.of(query.getResultList());
        } else if (name.equals("car")) {
            String s = "select order_item.id, u.username as bolim , c.name , c.cost , c.model , c.date_of_sanitary_release\n" +
                    "from order_item join orders o on o.id = order_item.orders_id join users u on u.id = o.user_id\n" + "join car c on o.by_id = c.id\n" +
                    " and order_item.zakaz_id = :id and payed = false";

            Query query = entityManager.createNativeQuery(s, EntitiyCar.class);
            query.setParameter("id", zakaz_id);
            return Optional.of(query.getResultList());
        } else if (name.equals("laptops")) {
            String s = "select order_item.id, u.username as bolim , l.name , l.cost , l.name , l.date_of_sanitary_release\n" +
                    "from order_item join orders o on o.id = order_item.orders_id join users u on u.id = o.user_id\n" +
                    "join laptops l on o.by_id = l.id\n" + "  and order_item.zakaz_id = :id and payed = false ";
            Query query = entityManager.createNativeQuery(s, EntitiyLaptops.class);
            query.setParameter("id", zakaz_id);
            return Optional.of(query.getResultList());

        }
        return null;
    }
}
