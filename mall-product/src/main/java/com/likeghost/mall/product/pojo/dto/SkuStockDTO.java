package com.likeghost.mall.product.pojo.dto;

import lombok.Data;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 1:47
 * @description
 */
@Data
public class SkuStockDTO {

    private Long skuId;
    private Integer stock;

//    public Integer getStock() {
//        return stock;
//    }
//
//    public void setStock(Integer stock) {
//        this.stock = stock;
//    }
//
//    public Long getSkuId() {
//        return skuId;
//    }
//
//    public void setSkuId(Long skuId) {
//        this.skuId = skuId;
//    }
}
