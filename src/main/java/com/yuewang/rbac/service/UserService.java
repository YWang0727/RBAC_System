package com.yuewang.rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.VO.UserPageVO;
import com.yuewang.rbac.model.VO.UserVO;
import com.yuewang.rbac.model.param.LoginParam;
import com.yuewang.rbac.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuewang.rbac.model.param.UserParam;

import java.util.Set;

/**
* @author Yue Wang
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-07 18:07:33
*/
public interface UserService extends IService<User> {
    User getUserByUsername(String username) throws ApiException;

    UserVO login(LoginParam loginParam) throws ApiException;

    void createUser(UserParam param);

    Set<Long> myPermission() throws ApiException;

    String updateToken() throws ApiException;

    void update(UserParam param);

    IPage<UserPageVO> selectPage(Page<UserPageVO> page);
}
