package com.example.demo.student.service;

import com.example.demo.student.dto.RegisterDto;
import com.example.demo.student.dto.UserDto;

public interface UserService {

    /**
     * 用户注册
     * @param registerDto
     */
    void register(RegisterDto registerDto);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserDto getUserByUsername(String username);
}
