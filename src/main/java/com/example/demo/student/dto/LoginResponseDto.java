package com.example.demo.student.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
}
