package com.likeghost.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LikeGhost
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class MallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallThirdPartyApplication.class, args);
    }

}
