package com.likeghost.mall.product.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/7 16:26
 * @description
 */
@Data
public class AttrVo implements Serializable {

    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Boolean searchType;
    /**
     * 属性图标
     */
    private String icon;


    /**
     * 是否多选
     */
    private Boolean valueType;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Boolean enable;
    /**
     * 所属分类
     */
    private Long catId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Boolean quickShow;

    private Long attrGroupId;

    private String categoryName;
    private String attrGroupName;
}
