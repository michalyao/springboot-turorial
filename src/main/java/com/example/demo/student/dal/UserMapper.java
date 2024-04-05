package com.example.demo.student.dal;
import org.apache.ibatis.annotations.Param;

import com.example.demo.student.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yao
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-05 10:03:36
* @Entity com.example.demo.student.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User findOneByUsername(@Param("username") String username);
}




