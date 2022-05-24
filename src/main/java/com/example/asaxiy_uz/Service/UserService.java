package com.example.asaxiy_uz.Service;

import com.example.asaxiy_uz.Dao.User;
import com.example.asaxiy_uz.Dao.UserDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Dto.ValidatorDto;
import com.example.asaxiy_uz.Helper.Constants.AppResponseCode;
import com.example.asaxiy_uz.Helper.Constants.AppResponseMassage;
import com.example.asaxiy_uz.Helper.Validator;
import com.example.asaxiy_uz.Mapping.UserMapping;
import com.example.asaxiy_uz.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseDto<Page<UserDto>> getUser(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageRequest);
        List<UserDto> userDtos = users
                .stream()
                .map(UserMapping::toDto)
                .collect(Collectors.toList());

        if (userDtos.size() > 0) {
            Page pages = new PageImpl(userDtos, users.getPageable(), users.getTotalElements());
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, pages);
        }
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR);
    }


    public ResponseDto<UserDto> addUser(UserDto userDto) {
        List<ValidatorDto> errors = Validator.addUser(userDto);
        if (errors.size() >= 1) {
            return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, userDto, errors);
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, userDto);
        }
        User user;
        try {
            user = UserMapping.toEntity(userDto);
            user.setId(null);
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, userDto);
        }
        return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, UserMapping.toDto(user));
    }

    public ResponseDto<UserDto> updateUser(UserDto userDto) {
        if (userRepository.existsById(userDto.getId())) {
            List<ValidatorDto> errors = Validator.addUser(userDto);
            if (errors.size() >= 1) {
                return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, userDto, errors);
            }
            User user;
            try {
                user = UserMapping.toEntity(userDto);
                userRepository.save(user);
            } catch (Exception e) {
                return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, userDto);
            }
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, UserMapping.toDto(user));
        }
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, userDto);
    }

    public ResponseDto<UserDto> deleteUser(String password) {
        if (userRepository.existsByPassword(password)) {
            Optional<User> user = userRepository.findByPassword(password);
            if (user.isPresent()) {
                try {
                    userRepository.delete(user.get());
                } catch (Exception e) {
                    return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR);
                }
            }
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, UserMapping.toDto(user.get()));
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND);
    }
}
