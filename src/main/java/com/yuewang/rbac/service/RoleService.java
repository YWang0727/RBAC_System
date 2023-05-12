package com.yuewang.rbac.service;

import com.yuewang.rbac.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Service
* @createDate 2023-05-07 18:25:11
*/
public interface RoleService extends IService<Role> {
    void removeByUserId(Serializable userId);

    void insertRolesByUserId(Long userId, Collection<Long> roleIds);
}
