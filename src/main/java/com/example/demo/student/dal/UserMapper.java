package com.example.demo.student.dal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.student.domain.User;
import org.apache.ibatis.annotations.Param;

/**
* @author yao
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-05 16:13:26
* @Entity com.example.demo.student.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User findOneByUsername(@Param("username") String username);
}




