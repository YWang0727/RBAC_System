package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.User;
import com.yuewang.rbac.service.UserService;
import com.yuewang.rbac.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-07 18:07:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




