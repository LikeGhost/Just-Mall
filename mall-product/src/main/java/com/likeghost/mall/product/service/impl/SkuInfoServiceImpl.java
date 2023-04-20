package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.SkuInfoDao;
import com.likeghost.mall.product.pojo.entity.SkuInfoEntity;
import com.likeghost.mall.product.service.SkuInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author LikeGhost
 * @date 2023/4/14 21:09
 * @description
 */

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageVO(page);
    }

    @Override
    public PageVO queryPageByConditions(Long catId, Long brandId, BigDecimal min, BigDecimal max, Map<String, Object> params) {
        LambdaQueryWrapper<SkuInfoEntity> queryWrapper = new LambdaQueryWrapper<>();


        if (brandId != null) {
            queryWrapper.eq(SkuInfoEntity::getBrandId, brandId);
        }
        if (catId != null && catId != 0) {
            queryWrapper.eq(SkuInfoEntity::getCatId, catId);
        }

        if (min != null) {
            queryWrapper.ge(SkuInfoEntity::getPrice, min);
        }
        if (max != null) {
            queryWrapper.le(SkuInfoEntity::getPrice, max);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(q -> q.like(SkuInfoEntity::getSkuId, key)
                    .or().like(SkuInfoEntity::getSkuName, key));
        }

        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageVO(page);
    }

}