package com.likeghost.mall;

import com.likeghost.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallProductApplicationTests {


    @Autowired
    BrandService brandService;
    @Test
    void contextLoads() {


        System.out.println( brandService.getById(1));
    }

}
