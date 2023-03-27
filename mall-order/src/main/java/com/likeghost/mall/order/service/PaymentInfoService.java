package com.likeghost.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:48:09
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageVo queryPage(Map<String, Object> params);
}

