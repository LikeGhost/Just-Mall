package com.likeghost.mall.product.feign;

import com.likeghost.mall.product.pojo.dto.SkuStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 1:39
 * @description
 */
@Component
@FeignClient(value = "mall-ware", path = "/ware/ware-sku")
public interface WareSkuFeignService {


    @PostMapping("/sku/stock")
    List<SkuStockDTO> getSkuStock(@RequestBody List<Long> skuIds);

}
