package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dto.BolimDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.BolimService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("bolim")
public class BolimController {

    private final BolimService bolimService;

    /* Barhca katigoriya yani bolimlarni korsatadi */
    @GetMapping("get-bolim")
    private ResponseDto<Page<BolimDto>> getAllBolim(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        return bolimService.getAllBolim(page, size);
    }

    /* Maydonlari boyicha get qilish uchun */
    @GetMapping("get-column")
    private ResponseDto<?> getColumn(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size, @RequestParam @NotNull String column) {
        return bolimService.getColumn(page, size, column);
    }

    /*add bolim qoshish uchun*/
    @PostMapping("add-bolim")
    private ResponseDto<BolimDto> addBolim(@RequestBody BolimDto bolimDto) {
        return bolimService.addBolim(bolimDto);
    }

    /*update bolim yana bolimni yangilash*/
    @PutMapping("update-bolim")
    private ResponseDto<BolimDto> updateBolim(@RequestBody BolimDto bolimDto) {
        return bolimService.updateBolim(bolimDto);
    }
}
