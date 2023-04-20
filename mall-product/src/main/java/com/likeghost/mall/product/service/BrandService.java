package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.product.pojo.entity.BrandEntity;

import java.util.Map;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/27 22:19
 * @description
 */
public interface BrandService extends IService<BrandEntity> {

    PageVO queryPage(Map<String, Object> params);

    PageVO queryPageByCatId(Map<String, Object> params, Long catId);
}

