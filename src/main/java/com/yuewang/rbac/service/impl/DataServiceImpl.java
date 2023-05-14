package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Data;
import com.yuewang.rbac.service.DataService;
import com.yuewang.rbac.mapper.DataMapper;
import com.yuewang.rbac.model.VO.DataPageVO;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【data】的数据库操作Service实现
* @createDate 2023-05-14 13:40:37
*/
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data>
    implements DataService {

    @Override
    public IPage<DataPageVO> selectPage(Page<DataPageVO> page){
        QueryWrapper<DataPageVO> queryWrapper = new QueryWrapper<>();
        IPage<DataPageVO> pages = baseMapper.selectPage(page, queryWrapper);
        //System.out.println(result.getRecords());
        return pages;
    }

}




