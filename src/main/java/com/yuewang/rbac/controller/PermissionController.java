package com.yuewang.rbac.controller;

import com.yuewang.rbac.annotation.Auth;
import com.yuewang.rbac.model.entity.Permission;
import com.yuewang.rbac.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PermissionController
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/14 11:39
 **/
@Slf4j
@RestController
@RequestMapping("/permission")
@Auth(id = 4000, name = "permission management")
@Api(tags = "Permission Management Interface")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    //@Auth(id = 1, name = "get all permissions")
    @ApiOperation(value = "Get all permissions")
    public List<Permission> getPermissionList(){
        return permissionService.list();
    }

}
