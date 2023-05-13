package com.yuewang.rbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.model.VO.RolePageVO;
import com.yuewang.rbac.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-07 18:25:11
* @Entity generator.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    int insertRolesByUserId(@Param("userId") Long userId, @Param("roleIds") Collection<Long> roleIds);

    int deleteByUserId(Serializable userId);

    IPage<RolePageVO> selectPage(Page<RolePageVO> page, @Param(Constants.WRAPPER) Wrapper<RolePageVO> wrapper);

    Set<Long> selectIdsByUserId(Long userId);
}




