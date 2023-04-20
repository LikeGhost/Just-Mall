package com.likeghost.mal.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LikeGhost
 * @date 2023/4/20 13:48
 * @description
 */

@SpringBootApplication(scanBasePackages = {"com.likeghost.*"}, exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class MallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSearchApplication.class, args);
    }

}
