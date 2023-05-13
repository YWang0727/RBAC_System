package com.yuewang.rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.model.VO.RolePageVO;
import com.yuewang.rbac.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuewang.rbac.model.param.RoleParam;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Service
* @createDate 2023-05-07 18:25:11
*/
public interface RoleService extends IService<Role> {

    void createRole(RoleParam param);

    void removeByUserId(Serializable userId);

    void insertRolesByUserId(Long userId, Collection<Long> roleIds);

    void updatePermissions(RoleParam param);

    IPage<RolePageVO> selectPage(Page<RolePageVO> page);

    Set<Long> getIdsByUserId(Long userId);
}
