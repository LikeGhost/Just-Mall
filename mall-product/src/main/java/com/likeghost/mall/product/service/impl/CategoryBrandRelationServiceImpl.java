package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.BrandDao;
import com.likeghost.mall.product.pojo.dao.CategoryBrandRelationDao;
import com.likeghost.mall.product.pojo.dao.CategoryDao;
import com.likeghost.mall.product.pojo.entity.BrandEntity;
import com.likeghost.mall.product.pojo.entity.CategoryBrandRelationEntity;
import com.likeghost.mall.product.pojo.entity.CategoryEntity;
import com.likeghost.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageVO queryPageByBrandId(Map<String, Object> params) {
        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<>();


        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageVO(page);
    }

    @Override
    public boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        BrandEntity brandEntity = brandDao.selectById(categoryBrandRelation.getBrandId());
        CategoryEntity categoryEntity = categoryDao.selectById(categoryBrandRelation.getCatId());
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCategoryName(categoryEntity.getName());

        return this.save(categoryBrandRelation);
    }

    @Override
    public PageVO queryPageByBrandId(Map<String, Object> params, Long brandId) {


        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity::getBrandId, brandId);
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageVO(page);

    }

    @Override
    public PageVO queryPageByCatId(Map<String, Object> params, Long catId) {
        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (catId != 0) {
            queryWrapper.eq(CategoryBrandRelationEntity::getCatId, catId);
        }


        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageVO(page);
    }

}