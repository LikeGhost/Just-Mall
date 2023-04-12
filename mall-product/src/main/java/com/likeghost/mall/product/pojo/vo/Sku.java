
package com.likeghost.mall.product.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LikeGhost
 * @date 2023/4/11 20:12
 * @description
 */
@Data
public class Sku {

    private List<SaleAttrValue> saleAttrs;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Image> images;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<String> memberPrice;

}