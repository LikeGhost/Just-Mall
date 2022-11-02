package com.likeghost.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.likeghost.mall.order.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:48:09
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
