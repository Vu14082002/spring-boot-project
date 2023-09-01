package com.edu.iuh.fit.springboot.Mapper;

import com.edu.iuh.fit.springboot.dto.UserDTO;
import com.edu.iuh.fit.springboot.entities.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getId(),user.getFirstname(),user.getLastname(),user.getEmail());
        return userDTO;
    }
    public static User mapToUser(UserDTO userDTO){
        User user = new User(userDTO.getId(),userDTO.getFirstname(),userDTO.getLastname(),userDTO.getEmail());
        return user;
    }
}
