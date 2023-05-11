package com.yuewang.rbac.controller;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.param.UserParam;
import com.yuewang.rbac.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
}
