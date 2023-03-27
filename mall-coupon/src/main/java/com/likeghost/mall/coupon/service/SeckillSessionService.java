package com.likeghost.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:00:07
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageVo queryPage(Map<String, Object> params);
}

