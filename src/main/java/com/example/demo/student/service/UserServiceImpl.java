package com.example.demo.student.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.demo.student.dal.RoleMapper;
import com.example.demo.student.dal.UserMapper;
import com.example.demo.student.domain.Role;
import com.example.demo.student.domain.User;
import com.example.demo.student.domain.UserRole;
import com.example.demo.student.dto.RegisterDto;
import com.example.demo.student.dto.UserDto;
import com.example.demo.student.dal.UserRoleMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public void register(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2L);
        userRoleMapper.insert(userRole);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userMapper.findOneByUsername(username);
        if (user == null) {
            return null;
        }
        List<UserRole> userRoles = userRoleMapper.findAllByUserId(user.getId());
        UserDto result = new UserDto();
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        if (CollectionUtil.isNotEmpty(userRoles)) {
            List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleMapper.findAllByIdIn(roleIds);
            result.setRoles(roles);
        }
        return result;
    }
}
