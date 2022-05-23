package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dao.Author;
import com.example.asaxiy_uz.Dto.AuthorDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Dto.ValidatorDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Helper.Validator;
import com.example.asaxiy_uz.Mapping.AuthorMapping;
import com.example.asaxiy_uz.Repository.AuthorRepositry;
import com.example.asaxiy_uz.Repository.CustemRepository.CustemAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepositry authorRepositry;

    private final CustemAuthorRepository custemAuthorRepository;

    public ResponseDto<Page<AuthorDto>> getAuthor(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Author> authors = authorRepositry.findAll(pageRequest);
        List<AuthorDto> authorDtos = authors
                .stream()
                .map(AuthorMapping::toDto)
                .collect(Collectors.toList());
        if(authorDtos.size() > 0){
            Page pages = new PageImpl(authorDtos,authors.getPageable(),authors.getTotalElements());

            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK,pages);
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR);
    }

    public ResponseDto<?> getAllParams(MultiValueMap<String, String> params) {
        Optional<?> authors = custemAuthorRepository.getAuthorParams(params);
        if(authors.isPresent()){
            return new ResponseDto<>(
                    true,AppResponseCode.OK,AppResponseMassage.OK,authors.get()
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR);
    }

    public ResponseDto<AuthorDto> addAuthor(AuthorDto authorDto) {
        List<ValidatorDto> errors = Validator.addAuthor(authorDto);
        if(errors.size() >= 1){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,authorDto,errors);
        }
        Author author;
        try {
            author = AuthorMapping.toEntity(authorDto);
            author.setId(null);
            authorRepositry.save(author);
        }catch (Exception e){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,authorDto);
        }
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AuthorMapping.toDto(author));
    }

    public ResponseDto<AuthorDto> updateAuthor(AuthorDto authorDto) {
        if (authorRepositry.existsById(authorDto.getId())){
            List<ValidatorDto> errors = Validator.addAuthor(authorDto);
            if(errors.size() >= 1){
                return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,authorDto);
            }
            Author author;
            try {
                author = AuthorMapping.toEntity(authorDto);
                authorRepositry.save(author);
            }catch (Exception e){
                return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,authorDto);
            }
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AuthorMapping.toDto(author));
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,authorDto);
    }
}
