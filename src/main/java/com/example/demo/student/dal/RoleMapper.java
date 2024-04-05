package com.example.demo.student.dal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.student.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
* @author yao
* @description 针对表【role】的数据库操作Mapper
* @createDate 2024-04-05 16:13:26
* @Entity com.example.demo.student.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findAllByIdIn(@Param("idList") Collection<Long> idList);
}




