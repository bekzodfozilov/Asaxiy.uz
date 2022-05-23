package com.example.asaxiy_uz.Service;


import com.example.asaxiy_uz.Dao.Author;
import com.example.asaxiy_uz.Dao.Bolim;
import com.example.asaxiy_uz.Dao.Books;
import com.example.asaxiy_uz.Dao.Entitiy.EntitiyBooks;
import com.example.asaxiy_uz.Dto.BooksDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Dto.ValidatorDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Helper.Validator;
import com.example.asaxiy_uz.Mapping.BooksMapping;
import com.example.asaxiy_uz.Repository.AuthorRepositry;
import com.example.asaxiy_uz.Repository.BolimRepository;
import com.example.asaxiy_uz.Repository.BooksRepository;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;

    private final CustemBooksRepository custemBooksRepository;

    private final BolimRepository bolimRepository;

    private final AuthorRepositry authorRepositry;


    public ResponseDto<?> getAllBooksOrParams(MultiValueMap<String, String> param) {
        Optional<?> books = custemBooksRepository.getAllBooksParams(param);
        if(books.isPresent()){
            return new ResponseDto<>(
                    true, AppResponseCode.OK, AppResponseMassage.OK,books.get()
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR);
    }

    public ResponseDto<?> addBooks(BooksDto booksDto) {
        List<ValidatorDto> errors = Validator.addBooks(booksDto);

        if(errors.size() > 1){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,booksDto,errors);
        }
        Books books = BooksMapping.ToEntitiy(booksDto);

        Optional<Bolim> bolim = bolimRepository.findById(booksDto.getBolim_id());

        if(bolim.isEmpty()){
            return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,booksDto);
        }
        books.setBolim_id(bolim.get().getId());

        Optional<Author> author = authorRepositry.findById(booksDto.getAuthor_id());

        if(author.isEmpty()){
            return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,booksDto);
        }
        books.setAuthor_id(author.get().getId());

        try {
            books.setId(null);
            booksRepository.save(books);
        }catch (Exception e){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,booksDto);
        }

        int id = books.getId();
       List<EntitiyBooks> entitiyBooks = custemBooksRepository.getBooksId(id);
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,entitiyBooks);

    }

    public ResponseDto<?> updateBooks(BooksDto booksDto) {
        if(booksRepository.existsById(booksDto.getId())) {
            List<ValidatorDto> errors = Validator.addBooks(booksDto);
            if (errors.size() >= 1) {
                return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, booksDto, errors);
            }
            Books books = BooksMapping.ToEntitiy(booksDto);

            Optional<Bolim> bolim = bolimRepository.findById(booksDto.getBolim_id());

            if (bolim.isEmpty()) {
                return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, booksDto);
            }
            books.setBolim_id(bolim.get().getId());

            Optional<Author> author = authorRepositry.findById(booksDto.getAuthor_id());

            if (author.isEmpty()) {
                return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, booksDto);
            }
            books.setAuthor_id(author.get().getId());

            try {
                booksRepository.save(books);
            } catch (Exception e) {
                return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, booksDto);
            }

            int id = books.getId();
            List<EntitiyBooks> entitiyBooks = custemBooksRepository.getBooksId(id);
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, entitiyBooks);
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,booksDto);
    }
}
