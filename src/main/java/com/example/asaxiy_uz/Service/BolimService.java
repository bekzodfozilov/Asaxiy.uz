package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dao.Bolim;
import com.example.asaxiy_uz.Dto.BolimDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Dto.ValidatorDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Helper.Validator;
import com.example.asaxiy_uz.Mapping.BolimMApping;
import com.example.asaxiy_uz.Repository.BolimRepository;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemBolimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BolimService {

    private final BolimRepository bolimRepository;

    private final CustemBolimRepository custemBolimRepository;


    public ResponseDto<Page<BolimDto>> getAllBolim(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Bolim> list = bolimRepository.findAll(pageRequest);
        List<BolimDto> bolimDto = list.
                stream()
                .map(BolimMApping::ToDto)
                .collect(Collectors.toList());
        Page<BolimDto> pages = new PageImpl<>(bolimDto,list.getPageable(),list.getTotalElements());
        if(bolimDto.size() > 0){
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK,pages);
        }else
            return new ResponseDto<>(
                    false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR
            );
    }

    public ResponseDto<?> getColumn(Integer page, Integer size, String column) {
        Optional<?> parametor = custemBolimRepository.getAllColumn(page,size,column);
        if(parametor.isPresent()){
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,parametor.get());
        }
        return new ResponseDto<>(false,null,null);
    }

    public ResponseDto<BolimDto> addBolim(BolimDto bolimDto) {
        List<ValidatorDto> errors = Validator.addBolim(bolimDto);
        Bolim bolim = null;
        if(errors.size() >= 1){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,bolimDto,errors);
        }
        try {
            bolimDto.setId(null);
           bolim = BolimMApping.ToEntity(bolimDto);
            bolimRepository.save(bolim);
        }catch (Exception e){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bolimDto);
        }
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,BolimMApping.ToDto(bolim));
    }

    public ResponseDto<BolimDto> updateBolim(BolimDto bolimDto) {
        if(bolimRepository.existsById(bolimDto.getId())) {
            List<ValidatorDto> errors = Validator.addBolim(bolimDto);
            Bolim bolim = null;
            if(errors.size() >= 1){
                return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,bolimDto);
            }
            try {
                bolim = BolimMApping.ToEntity(bolimDto);
                bolimRepository.save(bolim);
            }catch (Exception e){
                return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bolimDto);
            }
            return new ResponseDto<>(
                    true,AppResponseCode.OK,AppResponseMassage.OK,BolimMApping.ToDto(bolim)
            );
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,bolimDto);
    }
}
