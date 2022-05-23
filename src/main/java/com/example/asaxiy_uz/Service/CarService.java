package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dao.Bolim;
import com.example.asaxiy_uz.Dao.Car;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyCar;
import com.example.asaxiy_uz.Dto.CarDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Dto.ValidatorDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Helper.Validator;
import com.example.asaxiy_uz.Mapping.CarMapping;
import com.example.asaxiy_uz.Repository.BolimRepository;
import com.example.asaxiy_uz.Repository.CarRepository;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final CustemCarRepository custemCarRepository;

    private final BolimRepository bolimRepository;


    public ResponseDto<?> getCar(MultiValueMap<String, String> params) {
        Optional<?> cars = custemCarRepository.getCar(params);
        if (cars.isPresent()) {
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, cars.get());
        }
        return new ResponseDto<>(
                false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.NOT_FOUND
        );
    }

    @Transactional
    public ResponseDto<?> addCar(CarDto carDto) {
        List<ValidatorDto> errors = Validator.addCar(carDto);
        if (errors.size() >= 1) {
            return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, carDto, errors);
        }
        Optional<Bolim> bolim = bolimRepository.findById(carDto.getBolim_id());

        if (bolim.isPresent()) {
            Car car = CarMapping.toEntitiy(carDto);
            try {
                car.setBolim_id(bolim.get().getId());
                car.setId(null);
                carRepository.save(car);
            } catch (Exception e) {
                return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, carDto);
            }
            List<EntitiyCar> list = custemCarRepository.getId(bolim.get().getId());
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, list);
        }

        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,carDto);

    }

    public ResponseDto<?> updateCar(CarDto carDto) {
        if (carRepository.existsById(carDto.getId())) {
            List<ValidatorDto> errors = Validator.addCar(carDto);
            if (errors.size() >= 1) {
                return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, carDto, errors);
            }
            Optional<Bolim> bolim = bolimRepository.findById(carDto.getBolim_id());
            Car car;
            if (bolim.isPresent()) {
                try {
                    car = CarMapping.toEntitiy(carDto);
                    car.setBolim_id(bolim.get().getId());
                    carRepository.save(car);
                } catch (Exception e) {
                    return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, carDto);
                }
                List<EntitiyCar> entitiyCars = custemCarRepository.getId(car.getBolim_id());
                return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, entitiyCars);

            }
            return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, carDto);
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, carDto);
    }
}