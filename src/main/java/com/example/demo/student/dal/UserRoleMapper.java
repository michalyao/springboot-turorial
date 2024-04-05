package com.example.demo.student.dal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.student.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yao
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2024-04-05 10:26:27
* @Entity com.example.demo.student.dal.RoleMapper.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRole> findAllByUserId(@Param("userId") Long userId);
}




