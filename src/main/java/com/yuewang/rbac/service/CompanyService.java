package com.yuewang.rbac.service;

import com.yuewang.rbac.annotation.Auth;
import com.yuewang.rbac.model.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuewang.rbac.model.entity.Company;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author Yue Wang
* @description 针对表【company】的数据库操作Service
* @createDate 2023-05-14 13:37:34
*/
public interface CompanyService extends IService<Company> {

}
