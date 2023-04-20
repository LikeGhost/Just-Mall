package com.likeghost.mall.ware.pojo.vo;

import com.likeghost.mall.ware.pojo.entity.PurchaseDetailEntity;
import lombok.Data;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/17 0:10
 * @description
 */
@Data
public class CompletePurchasingVO {


    private Long purchaseId;

    private List<PurchaseDetailEntity> purchaseDetails;
}
