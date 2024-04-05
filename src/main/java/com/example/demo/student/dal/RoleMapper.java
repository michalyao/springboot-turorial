package com.example.demo.student.dal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.student.domain.Role;

/**
* @author yao
* @description 针对表【role】的数据库操作Mapper
* @createDate 2024-04-05 10:03:36
* @Entity com.example.demo.student.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findAllByIdIn(@Param("idList") Collection<Long> idList);
}




