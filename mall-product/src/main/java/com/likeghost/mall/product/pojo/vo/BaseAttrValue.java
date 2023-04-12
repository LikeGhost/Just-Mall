package com.likeghost.mall.product.pojo.vo;

import lombok.Data;

/**
 * @author LikeGhost
 * @date 2023/4/11 20:09
 * @description
 */
@Data
public class BaseAttrValue {

    private Long attrId;
    private String attrValue;
    private String attrName;
    private boolean quickShow;


}