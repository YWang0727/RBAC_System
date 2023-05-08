package com.yuewang.rbac.controller;

import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.VO.UserVO;
import com.yuewang.rbac.model.param.LoginParam;
import com.yuewang.rbac.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.AuthProvider;

@Slf4j
@Validated
@RestController
//@RequestMapping: assigned all methods in this class to a URL path
@RequestMapping("/auth")
@Api(tags = "Auth Management Interface")
public class AuthController {

    @Autowired
    private UserService userService;

    //@PostMapping: define the POST request under '/auth/login' should be handled by method 'login'
    //Put on a method in controller
    @PostMapping("/login")
    //@ApiOperation: add description(in swagger UI) for the operation of the API
    @ApiOperation(value = "Login by password")
    //get param from the request body which requested by HTTP
    //and do validation to the param
    public UserVO login(@RequestBody @Valid LoginParam param) throws ApiException {
        return userService.login(param);
    }

}
