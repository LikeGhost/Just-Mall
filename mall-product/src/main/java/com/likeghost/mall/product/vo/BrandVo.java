package com.likeghost.mall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/21 20:48
 * @description
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BrandVo {
    /**
     * 品牌id
     */
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    private String name;
    /**
     * 品牌logo地址
     */
    private Map<String, String> logo;
    /**
     * 介绍
     */
    private String description;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Boolean showStatus;
    /**
     * 检索首字母
     */
    private String firstLetter;
    /**
     * 排序
     */
    private Integer sort;

}
