package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Dto.BooksDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BooksController {

    private final BooksService booksService;

    /* Har bitta fildi boyicha get qiladi yeni malimotlarni tortib keladi aftori bilan*/
    @GetMapping("get-books-params")
    private ResponseDto<?> getAllBooksOrParams(@RequestParam MultiValueMap<String,String> param){
        return booksService.getAllBooksOrParams(param);
    }
    /* Har bir fildini tekshirb bazaga saqlaydi */
    @PostMapping("add-books")
    private ResponseDto<?> addBooks(@RequestBody BooksDto booksDto){
        return booksService.addBooks(booksDto);
    }
    /* Update yani bor kiroblarni yengilash uchun */
    @PutMapping("update-books")
    private ResponseDto<?> updateBooks(@RequestBody BooksDto booksDto){
        return booksService.updateBooks(booksDto);
    }




}
