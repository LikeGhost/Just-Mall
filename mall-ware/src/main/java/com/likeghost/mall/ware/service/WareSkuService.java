package com.likeghost.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.ware.pojo.dto.SkuStockDTO;
import com.likeghost.mall.ware.pojo.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageVO queryPage(Map<String, Object> params);

    PageVO queryPageByConditions(Long skuId, Long wareId, Map<String, Object> params);

    List<SkuStockDTO> getSkuStock(List<Long> skuIds);
}

