package com.likeghost.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.ware.pojo.dao.WareSkuDao;
import com.likeghost.mall.ware.pojo.entity.WareSkuEntity;
import com.likeghost.mall.ware.service.WareSkuService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author LikeGhost
 * @date 2023/4/15 16:18
 * @description
 */

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryPageByConditions(Long skuId, Long wareId, Map<String, Object> params) {


        LambdaQueryWrapper<WareSkuEntity> queryWrapper = new LambdaQueryWrapper<>();


        if (skuId != null) {
            queryWrapper.like(WareSkuEntity::getSkuId, skuId);
        }
        if (wareId != null) {
            queryWrapper.like(WareSkuEntity::getWareId, wareId);
        }

        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );
        return new PageVo(page);
    }

}