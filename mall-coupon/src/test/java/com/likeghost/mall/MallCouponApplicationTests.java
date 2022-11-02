package com.likeghost.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallCouponApplicationTests {

    @Value("${test}")
    private String test;
    @Test
    void contextLoads() {

        System.out.println(test);
    }

}
