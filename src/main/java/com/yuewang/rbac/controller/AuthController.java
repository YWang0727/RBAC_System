package com.yuewang.rbac.controller;

import cn.hutool.core.util.RandomUtil;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.VO.UserVO;
import com.yuewang.rbac.model.param.LoginParam;
import com.yuewang.rbac.model.param.RegisterParam;
import com.yuewang.rbac.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;
import java.util.Set;

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

    @PostMapping("/my-permission")
    @ApiOperation(value = "Get current UserVO's permission when route changes")
    public Set<Long> myPermission(@RequestBody @NotBlank String username) throws ApiException {
        // get user in context
        return userService.myPermission(username);
    }

    @PostMapping("/update-token")
    @ApiOperation(value = "Update token")
    public String updateToken(@RequestBody String username) throws ApiException {
        return userService.updateToken(username);
    }

//    @GetMapping("/code/{phone}")
//    @ApiOperation(value = "Get phone verification code")
//    public String sendCode(@PathVariable("phone") @NotBlank String phone){
//        //TODO check whethere this is code for this phone in redis
//
//        //TODO if phone is valid, send captcha to phone
//        String code = RandomUtil.randomNumbers(4);
//        //TODO send code to phone
//
//        //TODO save phone + code to redis
//
//        return "验证码已发送至手机号：" + phone + ",验证码为："+ code;
//    }


//    @PostMapping("/register")
//    @ApiOperation(value = "Register by phone")
//    public UserVO register(@RequestBody RegisterParam param){
//        //TODO get Code from redis according to phone
//        //TODO verify code and phone
//        return userService.register(param);
//    }

}
