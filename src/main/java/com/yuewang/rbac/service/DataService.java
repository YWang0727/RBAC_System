package com.yuewang.rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.model.VO.DataPageVO;
import com.yuewang.rbac.model.entity.Data;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuewang.rbac.model.entity.Data;
import org.springframework.stereotype.Repository;

/**
* @author Sarah Wang
* @description 针对表【data】的数据库操作Service
* @createDate 2023-05-14 13:40:37
*/
@Repository
public interface DataService extends IService<Data> {

    IPage<DataPageVO> selectPage(Page<DataPageVO> page);

}
