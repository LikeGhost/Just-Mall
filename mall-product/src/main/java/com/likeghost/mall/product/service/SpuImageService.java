package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.product.pojo.entity.SpuImageEntity;

import java.util.List;
import java.util.Map;

/**
 * spu图片
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface SpuImageService extends IService<SpuImageEntity> {

    PageVO queryPage(Map<String, Object> params);

    boolean saveImages(Long id, List<String> images);
}

