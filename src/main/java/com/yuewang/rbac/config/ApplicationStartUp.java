package com.yuewang.rbac.config;

import com.yuewang.rbac.model.entity.Permission;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ApplicationStartUp
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/14 21:01
 **/
@Component
public class ApplicationStartUp implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception{
        //scan and get all interfaces which need permission to access
        List<Permission> list = getAuthResources();
    }

    private List<Permission> getAuthResources(){
        
    }
}
