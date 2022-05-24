package com.example.asaxiy_uz.Helper;

import com.example.asaxiy_uz.Dao.UserDto;
import com.example.asaxiy_uz.Dto.*;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<ValidatorDto> addBolim(BolimDto bolimDto) {
        List<ValidatorDto> errors = new ArrayList<>();

        if(bolimDto.getName() == null || bolimDto.getName().trim().length() < 1){
            errors.add(new ValidatorDto("name", AppResponseMassage.EMPTY_FIELD));
        }
        return errors;
    }

    public static List<ValidatorDto> addBooks(BooksDto booksDto) {
        List<ValidatorDto> errors = new ArrayList<>();

        if(booksDto.getName() == null || booksDto.getName().trim().length() < 1){
            errors.add(new ValidatorDto("name",AppResponseMassage.EMPTY_FIELD));
        }
        if(booksDto.getCost() == null){
            errors.add(new ValidatorDto("cost",AppResponseMassage.EMPTY_FIELD));
        }else if(booksDto.getCost() < 1){
            errors.add(new ValidatorDto("cost",AppResponseMassage.MINUS_VALUE));
        }
        if(booksDto.getPublisher_date() == null || booksDto.getPublisher_date().trim().length() < 1){
            errors.add(new ValidatorDto("publisher_date",AppResponseMassage.EMPTY_FIELD));
        }
       if(booksDto.getPage_count() == null){
           errors.add(new ValidatorDto("pega_count",AppResponseMassage.EMPTY_FIELD));
       }else if(booksDto.getPage_count() < 1){
           errors.add(new ValidatorDto("page_count",AppResponseMassage.MINUS_VALUE));
       }
       if(booksDto.getGenre() == null || booksDto.getGenre().trim().length() < 1){
           errors.add(new ValidatorDto("genre",AppResponseMassage.EMPTY_FIELD));
       }
       if(booksDto.getAuthor_id() == null){
           errors.add(new ValidatorDto("auhtor_id",AppResponseMassage.EMPTY_FIELD));
       }else if(booksDto.getAuthor_id() < 1){
           errors.add(new ValidatorDto("author_id",AppResponseMassage.MINUS_VALUE));
       }
       if(booksDto.getBolim_id() == null){
           errors.add(new ValidatorDto("bolim_id",AppResponseMassage.EMPTY_FIELD));
       }else if(booksDto.getBolim_id() < 1){
           errors.add(new ValidatorDto("bolim_id",AppResponseMassage.MINUS_VALUE));
       }
       return errors;
    }

    public static List<ValidatorDto> addAuthor(AuthorDto authorDto) {
        List<ValidatorDto> errors = new ArrayList<>();

        if(authorDto.getFristname() == null || authorDto.getFristname().trim().length() < 1){
            errors.add(new ValidatorDto("fristname",AppResponseMassage.EMPTY_FIELD));
        }
        if(authorDto.getLastname() == null || authorDto.getLastname().trim().length() < 1){
            errors.add(new ValidatorDto("lastname",AppResponseMassage.EMPTY_FIELD));
        }
        if(authorDto.getBirthdate() == null || authorDto.getBirthdate().trim().length() < 1){
            errors.add(new ValidatorDto("brithdate",AppResponseMassage.EMPTY_FIELD));
        }
        return errors;
    }

    public static List<ValidatorDto> addCar(CarDto carDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(carDto.getName() == null || carDto.getName().trim().length() < 1){
            errors.add(new ValidatorDto("name",AppResponseMassage.EMPTY_FIELD));
        }
        if(carDto.getCost() == null){
            errors.add(new ValidatorDto("cost",AppResponseMassage.EMPTY_FIELD));
        }else if(carDto.getCost() < 1){
            errors.add(new ValidatorDto("csot",AppResponseMassage.MINUS_VALUE));
        }
        if(carDto.getDate_of_sanitary_release() == null || carDto.getDate_of_sanitary_release().trim().length() < 1){
            errors.add(new ValidatorDto("date_of_sanitary_release",AppResponseMassage.EMPTY_FIELD));
        }
        if(carDto.getModel() == null || carDto.getModel().trim().length() < 1){
            errors.add(new ValidatorDto("model",AppResponseMassage.EMPTY_FIELD));
        }
        if(carDto.getBolim_id() == null){
            errors.add(new ValidatorDto("bolim_id",AppResponseMassage.EMPTY_FIELD));
        }else if(carDto.getBolim_id() < 1){
            errors.add(new ValidatorDto("bolim_id",AppResponseMassage.MINUS_VALUE));
        }
        return errors;
    }

    public static List<ValidatorDto> addUser(UserDto userDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(userDto.getUsername() == null || userDto.getUsername().trim().length() < 1){
            errors.add(new ValidatorDto("username",AppResponseMassage.EMPTY_FIELD));
        }
        if(userDto.getPassword() == null || userDto.getPassword().trim().length() < 1){
            errors.add(new ValidatorDto("password",AppResponseMassage.EMPTY_FIELD));
        }
        return errors;
    }
}
