package com.likeghost.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/29 21:21
 * @description
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.likeghost.mall.*.pojo.dao")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(true);
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}
