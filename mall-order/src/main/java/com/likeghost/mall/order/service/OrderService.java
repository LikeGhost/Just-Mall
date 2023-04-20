package com.likeghost.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:48:09
 */
public interface OrderService extends IService<OrderEntity> {

    PageVO queryPage(Map<String, Object> params);
}

