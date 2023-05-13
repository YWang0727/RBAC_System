package com.yuewang.rbac.service;

import com.yuewang.rbac.model.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Set;

/**
* @author Sarah Wang
* @description 针对表【permission】的数据库操作Service
* @createDate 2023-05-07 18:25:05
*/
public interface PermissionService extends IService<Permission> {

    Set<Long> getIdsByUserId(Long userId);

}
