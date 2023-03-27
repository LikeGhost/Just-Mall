package com.likeghost.mall.thirdparty;

import com.likeghost.mall.thirdparty.minio.utils.MinioUtil;
import io.minio.http.Method;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallThirdPartyApplicationTests {

    @Autowired
    MinioUtil minioUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void minioTest() {
        String mall = minioUtil.getPreSignedObjectUrl(Method.PUT, "mall", "brands/logo.jpg",
                100000);
        System.out.println(mall);
    }

    @Test
    void minioDeleteTest() {
        minioUtil.delete("mall", "/brands/*");
    }

}
