package com.example.asaxiy_uz.Controller;

import com.example.asaxiy_uz.Dao.UserDto;
import com.example.asaxiy_uz.Dto.ResponseDto;
import com.example.asaxiy_uz.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    /* get users barcha foydalanuvchilarni get qilish*/
    @GetMapping("get-user")
    private ResponseDto<Page<UserDto>> getUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        return userService.getUser(page, size);
    }

    /* add user Yamgi foydalanuvchi qoshish*/
    @PostMapping("add-user")
    private ResponseDto<UserDto> addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    /* update user Foydalanuvchini yangiliash*/
    @PutMapping("update-user")
    private ResponseDto<UserDto> updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    /* dalete user akauntdan chiqib ketish*/
    @DeleteMapping("delete-user")
    private ResponseDto<UserDto> deleteUser(@RequestParam String password) {
        return userService.deleteUser(password);
    }

}
