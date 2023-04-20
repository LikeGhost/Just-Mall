package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.BrandDao;
import com.likeghost.mall.product.pojo.entity.BrandEntity;
import com.likeghost.mall.product.service.BrandService;
import com.likeghost.mall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/27 16:06
 * @description
 */

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

//    @Autowired
//    private MinioService minioService;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;


    @Override
    public PageVO queryPage(Map<String, Object> params) {
        // TODO: 2023/3/27 sql like key 优化，like会全表扫描，使用substr或其他方法走索引会好点
        String key = (String) params.get("key");
        LambdaQueryWrapper<BrandEntity> queryWrapper = new LambdaQueryWrapper<>();


        if (!StringUtils.isEmpty(key)) {

            queryWrapper.like(BrandEntity::getName, "%" + key + "%").or().eq(BrandEntity::getFirstLetter, key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );


//        String endpoint = (String) minioService.getEndpoint().get("endpoint");
//
//        for (BrandEntity brand : page.getRecords()) {
//            String logo = brand.getLogo();
//            brand.setLogo(endpoint + "/" + logo);
//        }


        return new PageVO(page);
    }

    @Override
    public PageVO queryPageByCatId(Map<String, Object> params, Long catId) {
        if (catId == null || catId == 0) {
//            PageVo pageVo = this.queryPage(params);
//            List<BrandEntity> list = (List<BrandEntity>) pageVo.getList();
            return this.queryPage(params);
        } else {
            return categoryBrandRelationService.queryPageByCatId(params, catId);
        }
    }


}