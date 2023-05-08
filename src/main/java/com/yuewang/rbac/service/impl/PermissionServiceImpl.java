package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Permission;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @author Sarah Wang
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
}




