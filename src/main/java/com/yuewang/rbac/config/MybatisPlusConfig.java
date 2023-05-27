package com.yuewang.rbac.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.yuewang.rbac.security.plugin.MyPaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description MyBatis-Plus pagination plugin configuration. Set sql interceptor before paging plugin.
 * @Author Yue Wang
 * @Date 2023/5/15 17:01
 **/

@Configuration
@MapperScan("com.yuewang.rbac.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // custom sql interceptor
        interceptor.addInnerInterceptor(new MyPaginationInterceptor());
        // pagination plugin
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;

    }
}
