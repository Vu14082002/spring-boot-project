package vn.edu.iuh.fit.myblog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.myblog.entity.Role;
import vn.edu.iuh.fit.myblog.entity.User;
import vn.edu.iuh.fit.myblog.exception.BlogApiException;
import vn.edu.iuh.fit.myblog.payload.LoginDto;
import vn.edu.iuh.fit.myblog.payload.RegisterDto;
import vn.edu.iuh.fit.myblog.repository.RoleRepository;
import vn.edu.iuh.fit.myblog.repository.UserRepository;
import vn.edu.iuh.fit.myblog.security.JwtTokenProvider;
import vn.edu.iuh.fit.myblog.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateToken(authenticate);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())
        ) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "username is already exists!!");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())
        ) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "email is already exists!!");
        }
        // convert registerDto to user entity
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // creat role
        Set<Role> roles = new HashSet<>();
        Role roleUser = roleRepository.findByName("ROLE_USER").get();
        // add role for user
        roles.add(roleUser);
        user.setRoles(roles);
        // save
        userRepository.save(user);
        return "User register success!!!";
    }
}
