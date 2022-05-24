package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemOrderItemRepository;
import com.example.asaxiy_uz.Repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final CustemOrderItemRepository custemOrderItemRepository;

    public ResponseDto<?> byOrder(Integer zakaz_id, String name) {
        if (orderItemRepository.existsById(zakaz_id)) {
            Optional<?> by_orders = custemOrderItemRepository.byOrder(zakaz_id, name);
            if (by_orders.isPresent()) {
                return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, by_orders.get());
            }
            return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR);
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND);
    }
}
