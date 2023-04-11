package com.likeghost.mall.product.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.likeghost.mall.product.pojo.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/10 16:13
 * @description
 */
@Data
public class AttrGroupWithAttrsVo {
    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catId;

    private List<AttrEntity> attrs;
}
