package com.yuewang.rbac.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j: an annotation in lombok, to generate a private static logger called 'log' automatically
@Slf4j
//@RestController: announce class 'UserController' as the controller of RESTful web service
//will turn the return value of the controller to JSON\XML or other format and pass to client via HTTP
//is the combination of @Controller and @ResponseBody
@RestController
@RequestMapping("/User")
public class UserController {

//    @PostMapping("/add")
//    //@Auth(id = 1, name = "新增用户")
//    @ApiOperation(value = "Add user")
//    public String createUser(@RequestBody @Validated(UserParam.CreateUser.class) UserParam param) {
//        userService.createUser(param);
//        return ACTION_SUCCESSFUL;
//    }

}
