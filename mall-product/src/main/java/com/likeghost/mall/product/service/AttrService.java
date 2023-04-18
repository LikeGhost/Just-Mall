package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.pojo.entity.AttrEntity;
import com.likeghost.mall.product.pojo.entity.ProductAttrValueEntity;
import com.likeghost.mall.product.pojo.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPageByCatId(Map<String, Object> params);

    PageVo queryPageByCatId(Map<String, Object> params, Long catId);

//    Boolean save(AttrVo attr);

    AttrVo getAttrInfo(Long attrId);

//    Boolean updateById(AttrVo attr);


    Boolean saveOrUpdate(AttrVo attr);

    PageVo queryPageByAttrTypeAndCatId(Map<String, Object> params, String attrType, Long catId);

    List<ProductAttrValueEntity> listBySpuId(Long spuId);

    boolean updateBaseAttrValues(Long spuId, List<ProductAttrValueEntity> baseAttrValues);
}

