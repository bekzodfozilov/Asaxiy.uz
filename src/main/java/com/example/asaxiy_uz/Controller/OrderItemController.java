package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orderitem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("by_order")
    private ResponseDto<?> byOrder(@RequestParam Integer zakaz_id, @RequestParam String name) {
        return orderItemService.byOrder(zakaz_id, name);
    }
}
