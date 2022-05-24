package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dto.CarDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    /* get params har bitta fildi boyohc malumot olish bolim classiga join qilib*/
    @GetMapping("get-car-params")
    private ResponseDto<?> getCar(@RequestParam MultiValueMap<String, String> params) {
        return carService.getCar(params);
    }

    /*add car yangi mashina qoshish*/
    @PostMapping("add-car")
    private ResponseDto<?> addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    /* update car car malumotlarini yangilash*/
    @PutMapping("update-car")
    private ResponseDto<?> updateCar(@RequestBody CarDto carDto) {
        return carService.updateCar(carDto);
    }
}
