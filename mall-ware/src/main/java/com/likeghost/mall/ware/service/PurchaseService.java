package com.likeghost.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.ware.pojo.entity.PurchaseEntity;
import com.likeghost.mall.ware.pojo.vo.CompletePurchasingVO;
import com.likeghost.mall.ware.pojo.vo.MergeItemsVO;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageVO queryPage(Map<String, Object> params);


    PageVO queryPageByConditions(Map<String, Object> params, Integer status);

    PageVO queryUnreceivedPage(Map<String, Object> params);

    boolean mergeItems(MergeItemsVO mergeItemsVo);

    boolean receivedPurchasing(List<Long> purchaseIds);

    boolean completePurchasing(CompletePurchasingVO completePurchasingVo);
}

