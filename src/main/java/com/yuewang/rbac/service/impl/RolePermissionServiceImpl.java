package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.RolePermission;
import com.yuewang.rbac.service.RolePermissionService;
import com.yuewang.rbac.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2023-05-07 18:25:15
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




