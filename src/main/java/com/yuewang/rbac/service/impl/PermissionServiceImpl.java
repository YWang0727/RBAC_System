package com.yuewang.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Permission;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.mapper.PermissionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
* @author Yue Wang
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-05-07 18:25:05
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public Set<Long> getIdsByUserId(Long userId) {
        return baseMapper.selectIdsByUserId(userId);
    }

    @Override
    public void deletePermissionByType(int type){
        // delete all API-type resources(type = 1)
        LambdaUpdateWrapper<Permission> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Permission::getType, type);
        baseMapper.delete(wrapper);
    }

    @Override
    public void insertPermissions(Collection<Permission> permissions){
        if(CollectionUtil.isEmpty(permissions)){
            return;
        }
        baseMapper.insertPermissions(permissions);
    }

}




