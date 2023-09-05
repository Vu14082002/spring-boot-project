package com.example.loginsystem.service.impl;

import com.example.loginsystem.dto.UserDto;
import com.example.loginsystem.enties.Role;
import com.example.loginsystem.enties.User;
import com.example.loginsystem.repository.RoleRepository;
import com.example.loginsystem.repository.UserRepository;
import com.example.loginsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
//    private BCryptPasswordEncoder passwordEncoder(String password){
//        return new BCryptPasswordEncoder().encode(password);
//    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstname()+" "+userDto.getLastname());
        user.setEmail(userDto.getEmail());
        // encrypt password using spring security
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role ==null){
            role = checkRoleExists();
        }
        user.getRoles().add(role);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream().map(value -> mapToUserDto(value)).collect(Collectors.toList());
        return userDtoList;
    }

    private Role checkRoleExists(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
    private UserDto mapToUserDto(User user){
        String[] srt = user.getName().split(" ");
        UserDto userDto = new UserDto();
        userDto.setFirstname(srt[0].toString());
        userDto.setLastname(srt[1].toString());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
