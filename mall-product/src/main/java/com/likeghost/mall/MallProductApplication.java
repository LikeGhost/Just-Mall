package com.likeghost.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LikeGhost
 * @date 2023/4/13 22:29
 * @description
 */


@SpringBootApplication(scanBasePackages = {"com.likeghost.mall.product", "com.likeghost.common.config"})
//@ComponentScan({"com.likeghost.mall.product","com.likeghost.common.config"})
@EnableDiscoveryClient
@EnableFeignClients
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
