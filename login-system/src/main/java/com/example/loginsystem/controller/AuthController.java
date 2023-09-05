package com.example.loginsystem.controller;

import com.example.loginsystem.dto.UserDto;
import com.example.loginsystem.enties.User;
import com.example.loginsystem.repository.UserRepository;
import com.example.loginsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @GetMapping({"/index"})
    public String home(){
        return"index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(Model model, @Valid @ModelAttribute("user") UserDto userDto, BindingResult result){

        User existsUser = userService.findUserByEmail(userDto.getEmail());
        if(existsUser !=null){
            result.rejectValue("email",null,"Email is already an account registration with same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> userDtoList = userService.findAllUser();
        model.addAttribute("users",userDtoList);
        return "users";
    }

}
