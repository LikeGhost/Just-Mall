package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageVo queryPage(Map<String, Object> params);
}

