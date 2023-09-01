package com.edu.iuh.fit.springboot.Mapper;


import com.edu.iuh.fit.springboot.dto.UserDTO;
import com.edu.iuh.fit.springboot.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
//    truong hop cac field khong giong nhau thi can phai chi dinh
//    @Mapping(source = "email", target = "emailAddress")
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class); // bien dich thi chay
    UserDTO mapToUserDto(User user);
    User mapToUser(UserDTO userDTO);

}
