package com.yuewang.rbac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.annotation.Auth;
import com.yuewang.rbac.model.VO.RolePageVO;
import com.yuewang.rbac.model.entity.Role;
import com.yuewang.rbac.model.param.RoleParam;
import com.yuewang.rbac.model.param.UserParam;
import com.yuewang.rbac.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/12 17:22
 **/
@Slf4j
@RestController
@RequestMapping("/role")
@Auth(id = 3000, name = "role Management Interface")
@Api(tags = "Role Management Interface")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    @Auth(id = 1, name = "add role")
    @ApiOperation(value = "Add role")
    public String createRole(@RequestBody @Validated(RoleParam.CreateRole.class) RoleParam param) {
        roleService.createRole(param);
        return "Action successful.";  //common response can be categorised into an Util class
    }

    @PostMapping("/delete")
    @Auth(id = 2, name = "delete role")
    @ApiOperation(value = "Delete role")
    public String deleteRole(@RequestBody Long[] ids) {
        roleService.removeRolesByIds(Arrays.asList(ids));
        return "Action successful.";
    }

    @PutMapping("/update")
    @Auth(id = 3, name = "update role")
    @ApiOperation(value = "Update role")
    public String updateRole(@RequestBody @Validated(RoleParam.UpdateResources.class) RoleParam param){
        roleService.updatePermissions(param);
        return "Action successful.";
    }

    @GetMapping("/list")
    //@Auth(id = 4, name = "get all roles")
    @ApiOperation(value = "Get all roles")
    public List<Role> getRoleList(){
        return roleService.list();
    }

    @GetMapping("/page/{current}&{pageSize}")
    //@Auth(id = 5, name = "page through role information")
    @ApiOperation(value = "Page through role information")
    //IPage: is an interface in MyBatis to encap the result of paging queries, and also offers methods to access the result
    public IPage<RolePageVO> getRolePage(@PathVariable("current") int current, @PathVariable("pageSize") int pageSize) {
        Page<RolePageVO> page = new Page<>();  //Page: implements from IPage
        OrderItem orderItem = new OrderItem();  //OrderItem: from MyBatis, to construct the order condition in SQL query
        orderItem.setColumn("id");  //order by "id"
        page.setCurrent(current).setSize(pageSize).addOrder(orderItem); //set current pages\order to PageVO
        return roleService.selectPage(page);
    }
}
