package com.yuewang.rbac.mapper;

import com.yuewang.rbac.model.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
* @author Sarah Wang
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-05-07 18:25:05
* @Entity generator.entity.Permission
*/
//@Repository: note the class as a component of DAO layer, and take the class as a bean
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<Long> selectIdsByUserId(Long userId);

    int insertPermissionByRoleId(@Param("roleId") Long roleId, @Param("permissionIds") Collection<Long> permissionIds);

    int deleteByRoleId(Serializable roleId);

    Set<Long> selectIdsByRoleId(Long roleId);

}




