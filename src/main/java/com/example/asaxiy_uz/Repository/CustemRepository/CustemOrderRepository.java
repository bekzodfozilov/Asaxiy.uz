package com.example.asaxiy_uz.Repository.CustemRepository;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyCar;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyLaptops;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyPhones;
import com.example.asaxiy_uz.Dao.OrderItem;
import com.example.asaxiy_uz.Mapping.OrderItemMapping;
import com.example.asaxiy_uz.Repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemOrderRepository {

    private final EntityManager entityManager;

    private final OrderItemRepository orderItemRepository;


    public Optional<?> orders(String name, Integer id, Integer size) {
        if (name.equals("books")) {
            String s = "select orders.id, u.username as bolim, b.name , b.cost , b.publisher_date , b.page_count , b.genre , a.lastname , a.fristname , a.birthdate\n" +
                    "from orders join users u on u.id = orders.user_id join books b on b.id = orders.by_id join author a on a.id = b.author_id\n" +
                    "and orders.id = :id";
            Query query = entityManager.createNativeQuery(s, EntitiyBooks.class);
            query.setParameter("id", id);
            OrderItem orderItem = OrderItemMapping.toEntiity(id, size);
            try {
                orderItemRepository.save(orderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Optional.of(query.getResultList());
        } else if (name.equals("car")) {
            String s = "select orders.id , u.username as bolim, c.name , c.cost , c.date_of_sanitary_release , c.model\n" +
                    "from orders join users u on u.id = orders.user_id join car c on orders.by_id = c.id and orders.id = :id";
            Query query = entityManager.createNativeQuery(s, EntitiyCar.class);
            query.setParameter("id", id);
            OrderItem orderItem = OrderItemMapping.toEntiity(id, size);
            try {
                orderItemRepository.save(orderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Optional.of(query.getResultList());
        } else if (name.equals("phones")) {
            String s = "select orders.id , u.username as bolim , p.name , p.cost , p.date_of_sanitary_release , p.model\n" +
                    "from orders join users u on u.id = orders.user_id join phones p on orders.by_id = p.id and orders.id = :id";

            Query query = entityManager.createNativeQuery(s, EntitiyPhones.class);
            query.setParameter("id", id);
            OrderItem orderItem = OrderItemMapping.toEntiity(id, size);
            try {
                orderItemRepository.save(orderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Optional.of(query.getResultList());
        } else if (name.equals("laptops")) {
            String s = "select orders.id , u.username as bolim , l.name , l.cost , l.date_of_sanitary_release , l.model\n" + "from\n" +
                    "orders join users u on u.id = orders.user_id join laptops l on orders.by_id = l.id and orders.id =:id";

            Query query = entityManager.createNativeQuery(s, EntitiyLaptops.class);
            query.setParameter("id", id);
            OrderItem orderItem = OrderItemMapping.toEntiity(id, size);
            try {
                orderItemRepository.save(orderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Optional.of(query.getResultList());
        }
        return null;
    }
}
