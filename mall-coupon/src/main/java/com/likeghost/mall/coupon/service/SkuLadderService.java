package com.likeghost.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:00:07
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageVo queryPage(Map<String, Object> params);
}

