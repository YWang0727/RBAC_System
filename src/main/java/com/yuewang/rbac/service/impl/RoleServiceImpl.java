package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Role;
import com.yuewang.rbac.service.RoleService;
import com.yuewang.rbac.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-05-07 18:25:11
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




