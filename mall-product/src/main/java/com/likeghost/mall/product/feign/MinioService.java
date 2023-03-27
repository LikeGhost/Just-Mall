package com.likeghost.mall.product.feign;

import com.likeghost.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/16 22:23
 * @description
 */

@Component
@FeignClient(value = "mall-third-party", path = "/minio")
public interface MinioService {
    @GetMapping("/presigned_url/{filepath}")
    R getPreSignedUrl(@PathVariable(name = "filepath") String filepath);

//    @PostMapping("/presigned_url")
//    String putPreSignedUrl(@RequestBody String filepath);


    @GetMapping("/endpoint")
    R getEndpoint();


}
