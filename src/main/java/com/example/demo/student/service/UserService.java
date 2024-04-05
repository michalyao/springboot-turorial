package com.example.demo.student.service;

import com.example.demo.student.dto.RegisterDto;
import com.example.demo.student.dto.UserDto;

public interface UserService {

    void register(RegisterDto registerDto);

    UserDto getUserByUsername(String username);
}
