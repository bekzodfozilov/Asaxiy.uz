package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dto.AuthorDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;

    /* get author Barcha authorlari chop etish*/
    @GetMapping("get-author")
    private ResponseDto<Page<AuthorDto>> getAuthor(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        return authorService.getAuthor(page, size);
    }

    /* har bir fildi boyich ishlash */
    @GetMapping("get-all-params")
    private ResponseDto<?> getAllParams(@RequestParam MultiValueMap<String, String> params) {
        return authorService.getAllParams(params);
    }

    /* add author barchi fildini tekshirb yangi author qoshish */
    @PutMapping("add-author")
    private ResponseDto<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    /* update yani author malumotlarini yangilash uchun*/
    @PutMapping("update-author")
    private ResponseDto<AuthorDto> updateAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(authorDto);
    }
}
