package com.likeghost.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LikeGhost
 * @date 2023/4/14 15:14
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.likeghost.*"})
//@SpringBootApplication
@EnableDiscoveryClient
//@MapperScan("com.likeghost.mall.ware.pojo.dao")
public class MallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWareApplication.class, args);
    }

}
