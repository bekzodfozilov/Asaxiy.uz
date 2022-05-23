package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.Car;
import com.example.asaxiy_uz.Dto.CarDto;

public class CarMapping {
    public static Car toEntitiy(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getName(),
                carDto.getCost(),
                carDto.getDate_of_sanitary_release(),
                carDto.getModel()
        );
    }
}
