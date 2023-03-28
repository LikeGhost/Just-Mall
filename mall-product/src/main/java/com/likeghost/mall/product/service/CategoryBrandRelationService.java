package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.pojo.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageVo queryPage(Map<String, Object> params);
}

