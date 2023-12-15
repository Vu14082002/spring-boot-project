package vn.edu.iuh.fit.myblog.service;

import vn.edu.iuh.fit.myblog.payload.LoginDto;
import vn.edu.iuh.fit.myblog.payload.RegisterDto;

public interface AuthService {
    String  login(LoginDto loginDto);
    String  register(RegisterDto registerDto);
}
