package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.product.pojo.entity.SpuInfoEntity;
import com.likeghost.mall.product.pojo.vo.SpuInfoSaveVO;

import java.util.Map;

/**
 * spu信息
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageVO queryPage(Map<String, Object> params);

    boolean save(SpuInfoSaveVO spuInfo);

    PageVO queryPageByConditions(Long brandId, Long catId, Integer publishStatus, Map<String, Object> params);

    boolean putSpu(Long spuId);
}

