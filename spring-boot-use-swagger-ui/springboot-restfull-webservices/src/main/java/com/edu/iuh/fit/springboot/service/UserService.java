package com.edu.iuh.fit.springboot.service;

import com.edu.iuh.fit.springboot.dto.UserDTO;
import com.edu.iuh.fit.springboot.entities.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUser();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO userDto);
    void deleteUserById(Long id);
}
