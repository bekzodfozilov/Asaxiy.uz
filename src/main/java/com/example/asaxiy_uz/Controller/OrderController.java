package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.OrderService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    /* karzingaka qoshish va zakaz idsini buyutma qilish uchn olish*/
    @PostMapping("order")
    private ResponseDto<?> order(@RequestParam @NotNull String name, @RequestParam Integer by_id, @RequestParam Integer user_id) {
        return orderService.order(name, by_id, user_id);
    }


}
