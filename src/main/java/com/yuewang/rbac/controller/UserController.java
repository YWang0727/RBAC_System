package com.yuewang.rbac.controller;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.entity.User;
import com.yuewang.rbac.model.param.UserParam;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

//@Slf4j: an annotation in lombok, to generate a private static logger called 'log' automatically
@Slf4j
//@RestController: announce class 'UserController' as the controller of RESTful web service
//will turn the return value of the controller to JSON\XML or other format and pass to client via HTTP
//is the combination of @Controller and @ResponseBody
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    //@Auth(id = 1, name = "add user")
    @ApiOperation(value = "Add user")
    public String createUser(@RequestBody @Validated(UserParam.CreateUser.class) UserParam param) {
        userService.createUser(param);
        return "Action successful.";  //common response can be categorised into an Util class
    }

    @DeleteMapping("/delete")
    //@Auth(id = 2, name = "delete user")
    @ApiOperation(value = "Delete user")
    public String deleteUser(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {  //a utility method that checks if an array is empty or null
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        userService.removeByIds(Arrays.asList(ids));  //remove matched users from database
        return "Action successful.";
    }

    @PutMapping("/update")
    //@Auth(id = 3, name = "update user")
    @ApiOperation(value = "Update user")
    public String updateUser(@RequestBody @Validated(UserParam.Update.class) UserParam param) {
        userService.update(param);
        return "Action successful.";
    }

    @GetMapping("/get/{id}")
    //@Auth(id = 4, name = "get user info by user id")
    @ApiOperation(value = "Get user info by user id")
    //@ApiParam: is from Swagger, to describe the param's info in the API interface
    //@PathVariable: is from SpringMVC, to bind the var in the request path to the method's param
    public User getUserById(@ApiParam(value = "User ID", required = true) @PathVariable("id") Long id){
        return userService.getById(id);
    }

    @GetMapping("/permissions/{id}")
    //@Auth(id = 5, name = "get user permissions by user id")
    @ApiOperation(value = "Gget user permissions by user id")
    public Set<Long> getPermissionsByUserId(@ApiParam(value = "User ID", required = true) @PathVariable("id") Long id){
        return permissionService.getIdsByUserId(id);
    }

//    @GetMapping("/page/{current}&{pageSize}")
//    @Auth(id = 6, name = "分页查询用户信息")
//    @ApiOperation(value = "Page through user information")
//    public IPage<UserPageVO> getPage(@PathVariable("current") int current, @PathVariable("pageSize") int pageSize) {

//    }
}
