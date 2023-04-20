package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.product.pojo.entity.AttrAttrGroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface AttrAttrGroupRelationService extends IService<AttrAttrGroupRelationEntity> {

    PageVO queryPage(Map<String, Object> params);

    PageVO queryPage(Map<String, Object> params, Long attrId);
}

