package com.yuewang.rbac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuewang.rbac.annotation.Auth;
import com.yuewang.rbac.model.VO.DataPageVO;
import com.yuewang.rbac.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DataController
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/14 15:59
 **/
@Slf4j
@RestController
@RequestMapping("/data")
@Auth(id = 6000, name = "Data Management Interface")
@Api(tags = "Data Management Interface")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/page/{current}")
    //@Auth: add Auth annotations for modular management of interface permissions
    //@Auth(id = 1, name = "get data based on current page")
    @ApiOperation(value = "Get data based on current page")
    public IPage<DataPageVO> getPage(@PathVariable("current") int current) {
        Page<DataPageVO> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("data.created_time");  //order by created time
        orderItem.setAsc(false);  //order by desc
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setColumn("data.id");  //order by id
        orderItem2.setAsc(true);  //order by asc
        page.setCurrent(current).addOrder(orderItem).addOrder(orderItem2);
        return dataService.selectPage(page);
    }
}
