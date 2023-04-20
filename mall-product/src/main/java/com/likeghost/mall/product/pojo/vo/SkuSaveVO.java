
package com.likeghost.mall.product.pojo.vo;

import com.likeghost.mall.product.pojo.bo.SaleAttrValue;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LikeGhost
 * @date 2023/4/11 20:12
 * @description
 */
@Data
public class SkuSaveVO {

    private List<SaleAttrValue> saleAttrs;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Image> images;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<String> memberPrice;


    @Data
    public class Image {

        private Integer defaultImg;
        private String imgUrl;
    }

}