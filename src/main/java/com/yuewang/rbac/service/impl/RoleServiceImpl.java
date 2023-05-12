package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Role;
import com.yuewang.rbac.service.RoleService;
import com.yuewang.rbac.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-05-07 18:25:11
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Override
    public void removeByUserId(Serializable userId){
        //baseMapper: a common basic interface in MyBatis, defined CRUD methods
        baseMapper.deleteById(userId);
    }

    @Override
    public void insertRolesByUserId(Long userId, Collection<Long> roleIds){
        baseMapper.insertRolesByUserId(userId, roleIds);
    }

}




