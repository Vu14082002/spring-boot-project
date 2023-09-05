package com.example.loginsystem.service;

import com.example.loginsystem.dto.UserDto;
import com.example.loginsystem.enties.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUser();
}
