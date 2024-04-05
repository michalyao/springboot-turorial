package com.example.demo.student.api;

import com.example.demo.student.dto.LoginRequestDto;
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
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userService;
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAccessToken(jwtUtil.generateToken(authentication));
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        UserDto user = userService.getUserByUsername(registerDto.getUsername());
        if (user != null) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
       userService.register(registerDto);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
