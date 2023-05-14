package com.yuewang.rbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.model.VO.DataPageVO;
import com.yuewang.rbac.model.entity.Data;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Yue Wang
* @description 针对表【data】的数据库操作Mapper
* @createDate 2023-05-14 13:40:37
* @Entity .model.entity.Data
*/
public interface DataMapper extends BaseMapper<Data> {

    IPage<DataPageVO> selectPage(Page<DataPageVO> page, @Param(Constants.WRAPPER) Wrapper<DataPageVO> wrapper);
}




