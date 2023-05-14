package com.yuewang.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.model.entity.Company;
import com.yuewang.rbac.service.CompanyService;
import com.yuewang.rbac.mapper.CompanyMapper;
import com.yuewang.rbac.model.entity.Company;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【company】的数据库操作Service实现
* @createDate 2023-05-14 13:37:34
*/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
    implements CompanyService{

}




