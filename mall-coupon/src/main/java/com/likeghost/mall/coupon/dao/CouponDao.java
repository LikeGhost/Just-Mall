package com.likeghost.mall.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.likeghost.mall.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:00:08
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
