package com.likeghost.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:00:08
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageVo queryPage(Map<String, Object> params);
}

