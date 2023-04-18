package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.pojo.entity.SpuInfoEntity;
import com.likeghost.mall.product.pojo.vo.SpuInfoSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageVo queryPage(Map<String, Object> params);

    boolean save(SpuInfoSaveVo spuInfo);

    PageVo queryPageByConditions(Long brandId, Long catId, Integer publishStatus, Map<String, Object> params);
}

