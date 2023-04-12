package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.SkuImageDao;
import com.likeghost.mall.product.pojo.entity.SkuImageEntity;
import com.likeghost.mall.product.service.SkuImageService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("skuImagesService")
public class SkuImageServiceImpl extends ServiceImpl<SkuImageDao, SkuImageEntity> implements SkuImageService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<SkuImageEntity> page = this.page(
                new Query<SkuImageEntity>().getPage(params),
                new QueryWrapper<SkuImageEntity>()
        );

        return new PageVo(page);
    }

}