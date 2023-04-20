package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.product.pojo.entity.SkuInfoEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * sku信息
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageVO queryPage(Map<String, Object> params);

    PageVO queryPageByConditions(Long catId, Long brandId, BigDecimal min, BigDecimal max, Map<String, Object> params);
}

