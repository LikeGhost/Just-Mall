
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
public class SpuInfoSaveVO {

    private String spuName;
    private String spuDescription;
    private Long catId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;
    private List<String> detail;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrValue> baseAttrs;
    private List<SkuSaveVO> skus;


    @Data
    public class BaseAttrValue {

        private Long attrId;
        private String attrValue;
        private String attrName;
        private boolean quickShow;


    }

}