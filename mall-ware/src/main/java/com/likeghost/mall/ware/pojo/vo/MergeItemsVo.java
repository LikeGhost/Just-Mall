package com.likeghost.mall.ware.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/16 15:49
 * @description
 */
@Data
public class MergeItemsVo {
    private Long purchaseId;
    private List<Long> items;
}
