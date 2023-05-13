package com.yuewang.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.mapper.PermissionMapper;
import com.yuewang.rbac.model.VO.RolePageVO;
import com.yuewang.rbac.model.entity.Role;
import com.yuewang.rbac.model.entity.User;
import com.yuewang.rbac.model.param.RoleParam;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.service.RoleService;
import com.yuewang.rbac.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
* @author Yue Wang
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-05-07 18:25:11
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void createRole(RoleParam param){
        //check exist
        if (lambdaQuery().eq(Role::getName, param.getName()).one() != null) {
            throw new ApiException(ResultCode.FAILED,"Role name already exists.");
        }
        //add role
        Role role = new Role().setName(param.getName());
        save(role);
        if(CollectionUtil.isEmpty(param.getPermissionIds())){
            return;
        }
        //add permission
        permissionMapper.insertPermissionByRoleId(param.getId(), param.getPermissionIds());
    }

    @Override
    public void removeByUserId(Serializable userId){
        //baseMapper: a common basic interface in MyBatis, defined CRUD methods
        baseMapper.deleteByUserId(userId);
    }

    @Override
    public void insertRolesByUserId(Long userId, Collection<Long> roleIds){
        baseMapper.insertRolesByUserId(userId, roleIds);
    }

    @Override
    public void updatePermissions(RoleParam param){
        permissionMapper.deleteByRoleId(param.getId());
        if(CollectionUtil.isEmpty(param.getPermissionIds())){
            return;
        }
        permissionMapper.insertPermissionByRoleId(param.getId(), param.getPermissionIds());
    }

    @Override
    public IPage<RolePageVO> selectPage(Page<RolePageVO> page){
        // set query condition
        QueryWrapper<RolePageVO> queryWrapper = new QueryWrapper<>();
        // get page list
        IPage<RolePageVO> pages = baseMapper.selectPage(page, queryWrapper);
        // get roles' permission
        for (RolePageVO vo : pages.getRecords()) {
            vo.setPermissionIds(permissionMapper.selectIdsByRoleId(vo.getId()));
        }
        return pages;
    }

    @Override
    public Set<Long> getIdsByUserId(Long userId){
        return baseMapper.selectIdsByUserId(userId);
    }

}




