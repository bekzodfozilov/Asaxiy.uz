package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dao.Order;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Mapping.OrderMapping;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemOrderRepository;
import com.example.asaxiy_uz.Repository.OrderRepository;
import com.example.asaxiy_uz.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustemOrderRepository custemOrderRepository;

    private final UserRepository userRepository;


    public ResponseDto<?> order(String name, Integer by_id, Integer user_id) {

        if (userRepository.existsById(user_id)) {
            Order order;
            try {
                order = OrderMapping.toOrder(by_id, user_id);
                orderRepository.save(order);
            } catch (Exception e) {
                return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR);
            }
            List<Order> orderss = orderRepository.findAll();
            Optional<?> orders = custemOrderRepository.orders(name, order.getId(), orderss.size());
            if (orders.isPresent()) {
                return new ResponseDto<>(true, AppResponseCode.OK, "Sizning zakaz idingiz --> " + orderss.size(), orders.get());
            }
        }
        return null;
    }
}
