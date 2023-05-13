package com.yuewang.rbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.model.VO.UserPageVO;
import com.yuewang.rbac.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author Sarah Wang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-07 18:07:33
* @Entity generator.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    IPage<UserPageVO> selectPage(Page<UserPageVO> page, @Param(Constants.WRAPPER) Wrapper<UserPageVO> wrapper);
}




