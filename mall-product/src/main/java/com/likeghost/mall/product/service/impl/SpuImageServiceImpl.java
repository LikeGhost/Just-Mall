package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.SpuImageDao;
import com.likeghost.mall.product.pojo.entity.SpuImageEntity;
import com.likeghost.mall.product.service.SpuImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuImagesService")
public class SpuImageServiceImpl extends ServiceImpl<SpuImageDao, SpuImageEntity> implements SpuImageService {

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<SpuImageEntity> page = this.page(
                new Query<SpuImageEntity>().getPage(params),
                new QueryWrapper<SpuImageEntity>()
        );

        return new PageVO(page);
    }

    @Override
    public boolean saveImages(Long spuId, List<String> images) {

        if (images != null && !images.isEmpty()) {
            List<SpuImageEntity> collect = images.stream().map(image -> {
                SpuImageEntity spuImage = new SpuImageEntity();
                spuImage.setSpuId(spuId);
                spuImage.setImgUrl(image);
                return spuImage;
            }).collect(Collectors.toList());
            return this.saveBatch(collect);
        }
        return true;
    }

}