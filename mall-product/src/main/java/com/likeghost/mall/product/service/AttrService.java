package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.pojo.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(Map<String, Object> params);
}

