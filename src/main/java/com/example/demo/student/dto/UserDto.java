package com.example.demo.student.dto;

import com.example.demo.student.domain.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String password;
    private List<Role> roles;
}
