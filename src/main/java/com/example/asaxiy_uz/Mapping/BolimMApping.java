package com.example.asaxiy_uz.Mapping;

import com.example.asaxiy_uz.Dao.Bolim;
import com.example.asaxiy_uz.Dto.BolimDto;

public class BolimMApping {
    public static BolimDto ToDto(Bolim bolim) {
        return new BolimDto(
                bolim.getId(),
                bolim.getName()
        );
    }

    public static Bolim ToEntity(BolimDto bolimDto) {
        return new Bolim(
                bolimDto.getId(),
                bolimDto.getName()
        );
    }
}
