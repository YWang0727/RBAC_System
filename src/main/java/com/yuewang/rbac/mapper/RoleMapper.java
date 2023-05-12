package com.yuewang.rbac.mapper;

import com.yuewang.rbac.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-07 18:25:11
* @Entity generator.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    int insertRolesByUserId(@Param("userId") Long userId, @Param("roleIds") Collection<Long> roleIds);
}




