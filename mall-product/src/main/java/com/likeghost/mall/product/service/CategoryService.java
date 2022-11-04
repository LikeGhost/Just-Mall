package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.utils.PageUtils;
import com.likeghost.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listTree();
}

