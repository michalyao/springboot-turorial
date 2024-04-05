package com.example.demo.student.api;

import com.example.demo.student.dto.LoginDto;
import com.example.demo.student.dto.LoginResponseDto;
import com.example.demo.student.dto.RegisterDto;
import com.example.demo.student.dto.UserDto;
import com.example.demo.student.security.JwtUtil;
import com.example.demo.student.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        UserDto user = userService.getUserByUsername(registerDto.getUsername());
        if (user != null) {
            return new ResponseEntity<>("用户名已存在", HttpStatus.BAD_REQUEST);
        }
        userService.register(registerDto);
        return new ResponseEntity<>("用户注册成功", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtUtil.generateToken(authenticate));
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
