package com.likeghost.mall.product.feign;

import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.dto.ProductEsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 14:54
 * @description
 */
@Component
@FeignClient(value = "mall-search", path = "/search/save")
public interface ElasticsearchSaveFeignService {

    @PostMapping("/product")
    R saveProduct(@RequestBody List<ProductEsDTO> products);
}
