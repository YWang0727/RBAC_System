package com.yuewang.rbac.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.VO.UserVO;
import com.yuewang.rbac.model.entity.User;
import com.yuewang.rbac.model.param.LoginParam;
import com.yuewang.rbac.security.JwtManager;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.service.RoleService;
import com.yuewang.rbac.service.UserService;
import com.yuewang.rbac.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author Yue Wang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-07 18:07:33
*/
@Slf4j
@Service  //to note an implementation class in MyBatis - which is a bean
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUserByUsername(String username) throws ApiException {
        if(StrUtil.isBlank(username)){
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        return this.lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public UserVO login(LoginParam loginParam) throws ApiException {
        // Verify user from database
        User user = this.lambdaQuery()  //a lambda style query constructor provided by MyBatisPlus for building SQL query language
                .eq(StrUtil.isNotBlank(loginParam.getUsername()), User::getUsername, loginParam.getUsername())
//                .eq(StrUtil.isNotBlank(loginParam.getPhone()), User::getPhone, loginParam.getPhone())
                .one();  //return one data if it is not null

        // Throw error if user or password is wrong
        if(user == null || !passwordEncoder.matches(loginParam.getPassword(), user.getPassword())){
            throw new ApiException(ResultCode.VALIDATE_FAILED, "Username or password is incorrect！");
        }
        // Generate token, get and put user permissions in UserVO object
        UserVO userVO = new UserVO();
        userVO.setId(user.getId()).setUsername(user.getUsername())
                .setToken(JwtManager.generate(user.getUsername()))  //generate a JWT for authentication and authorisation
                .setPermissionIds(permissionService.getIdsByUserId(user.getId()));
        return userVO;
    }

}




