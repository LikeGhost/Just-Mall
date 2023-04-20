package com.likeghost.mal.search.pojo.dto;

import com.likeghost.mal.search.pojo.bo.SaleAttrValue;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/19 23:42
 * @description
 */
@Data
public class ProductEsDTO {

    private List<SaleAttrValue> attrs;

    private Long brandId;

    private String brandImg;
    private String brandName;
    private Long catId;

    private String categoryName;
    private Long hotScore;
    private Long saleCount;
    private Long skuId;
    private String skuImg;
    private BigDecimal skuPrice;
    private String skuTitle;
    private Long spuId;

    private Boolean hasStock;


}
