package com.edu.iuh.fit.springboot.service.impl;

import com.edu.iuh.fit.springboot.Mapper.AutoUserMapper;
import com.edu.iuh.fit.springboot.dto.UserDTO;
import com.edu.iuh.fit.springboot.entities.User;
import com.edu.iuh.fit.springboot.exception.EmailAlreadyExistsException;
import com.edu.iuh.fit.springboot.exception.ResultNotFoundException;
import com.edu.iuh.fit.springboot.repository.UserRepository;
import com.edu.iuh.fit.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // ko can autowired vi tru 4.3 tro di thi mac dinh cso 1 constructor Autowired nen ko can lam
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
//        c1
//        convert UserDTo to JPA User
//        User user = UserMapper.mapToUser(userDTO);
//        User savedUser = userRepository.save(user);
//        return  modelMapper.map(savedUser, UserDTO.class);

//      c2
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already Exists for user");
        }
        User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
        User savedUser = userRepository.save(user);
        UserDTO saveUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return saveUserDto;

    }

    @Override
    public List<UserDTO> getAllUser() {
        modelMapper.map(userRepository.findAll(), UserDTO.class);
        return userRepository.findAll().stream().map(value ->
                modelMapper.map(value, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> modelMapper.map(user, UserDTO.class)).orElseThrow(() -> new ResultNotFoundException("User", "ID", id)
        );
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        Optional<User> savedUser = userRepository.findById(userDTO.getId());
        User user = savedUser.orElseThrow(() -> new ResultNotFoundException("User", "ID", userDTO.getId()));
        return modelMapper.map(user, UserDTO.class);
       
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResultNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }


}
