package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.constant.ProductConstant;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.AttrDao;
import com.likeghost.mall.product.pojo.dao.AttrGroupDao;
import com.likeghost.mall.product.pojo.entity.AttrAttrGroupRelationEntity;
import com.likeghost.mall.product.pojo.entity.AttrEntity;
import com.likeghost.mall.product.pojo.entity.AttrGroupEntity;
import com.likeghost.mall.product.pojo.entity.CategoryEntity;
import com.likeghost.mall.product.pojo.vo.AttrVo;
import com.likeghost.mall.product.service.AttrAttrGroupRelationService;
import com.likeghost.mall.product.service.AttrService;
import com.likeghost.mall.product.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/8 15:24
 * @description
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {


    @Autowired
    private AttrAttrGroupRelationService attrAttrGroupRelationService;
    //导致循环依赖
//    @Autowired
//    private AttrGroupService attrGroupService;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageVo queryPageByCatId(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryPageByCatId(Map<String, Object> params, Long catId) {

        String key = (String) params.get("key");

        LambdaQueryWrapper<AttrEntity> queryWrapper = new LambdaQueryWrapper<AttrEntity>();

        if (catId != 0) {
            queryWrapper.eq(AttrEntity::getCatId, catId);
        }

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.like(AttrEntity::getAttrName, "%" + key + "%");
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        List<AttrVo> attrVoList = page.getRecords().stream().map((attrEntity -> {
            AttrVo attrVo = new AttrVo();
            BeanUtils.copyProperties(attrEntity, attrVo);
            AttrAttrGroupRelationEntity relation = attrAttrGroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrGroupRelationEntity>()
                    .eq(AttrAttrGroupRelationEntity::getAttrId, attrEntity.getAttrId()));
            if (relation != null && relation.getAttrGroupId() != null) {
                // TODO: 2023/4/10 使用attrGroupService会导致循环依赖
                AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
                attrVo.setAttrGroupName(attrGroup.getAttrGroupName());
            }
            CategoryEntity category = categoryService.getById(attrEntity.getCatId());
            attrVo.setCategoryName(category.getName());
            return attrVo;
        })).collect(Collectors.toList());


        PageVo pageVo = new PageVo(page);
        pageVo.setList(attrVoList);
        return pageVo;
    }

    @Override
    public PageVo queryPageByAttrTypeAndCatId(Map<String, Object> params, String attrType, Long catId) {

//        String key= (String) params.get("key");

        LambdaQueryWrapper<AttrEntity> queryWrapper = new LambdaQueryWrapper<AttrEntity>();

        if (catId != 0) {
            queryWrapper.eq(AttrEntity::getCatId, catId);
        }

//        if(!StringUtils.isEmpty(key)){
//            queryWrapper.like(AttrEntity::getAttrName,key);
//        }

        if (attrType != null) {
            ProductConstant.AttrType type = ProductConstant.AttrType.valueOf(attrType.toUpperCase() + "_ATTR");
            queryWrapper.eq(AttrEntity::getAttrType, type.getCode());
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

//        List<AttrVo> attrVoList = page.getRecords().stream().map((attrEntity -> {
//            AttrVo attrVo = new AttrVo();
//            BeanUtils.copyProperties(attrEntity, attrVo);
//            AttrAttrGroupRelationEntity relation = attrAttrGroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrGroupRelationEntity>()
//                    .eq(AttrAttrGroupRelationEntity::getAttrId, attrEntity.getAttrId()));
//            if (relation!=null&&relation.getAttrGroupId() != null) {
//                // TODO: 2023/4/10 使用attrGroupService会导致循环依赖
//                AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
//                attrVo.setAttrGroupName(attrGroup.getAttrGroupName());
//            }
//            CategoryEntity category = categoryService.getById(attrEntity.getCatId());
//            attrVo.setCategoryName(category.getName());
//            return attrVo;
//        })).collect(Collectors.toList());


        PageVo pageVo = new PageVo(page);
//        pageVo.setList(attrVoList);
        return pageVo;
    }

    @Override
    @Transactional
    public Boolean saveOrUpdate(AttrVo attr) {
        boolean result = true;
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        result = result && this.saveOrUpdate(attrEntity);

        if (attr.getAttrType() == ProductConstant.AttrType.BASE_ATTR.getCode()) {
            BeanUtils.copyProperties(attrEntity, attr);

            AttrAttrGroupRelationEntity relation = new AttrAttrGroupRelationEntity();

            BeanUtils.copyProperties(attr, relation);

            result = result && attrAttrGroupRelationService.saveOrUpdate(relation,
                    new LambdaUpdateWrapper<AttrAttrGroupRelationEntity>()
                            .eq(AttrAttrGroupRelationEntity::getAttrId, relation.getAttrId()));

        }


        return result;
    }
//    @Override
//    @Transactional
//    public Boolean save(AttrVo attr) {
//        Boolean result=true;
//        AttrEntity attrEntity = new AttrEntity();
//        BeanUtils.copyProperties(attr,attrEntity);
//        result=result&&this.save(attrEntity);
//        AttrAttrGroupRelationEntity relation = new AttrAttrGroupRelationEntity();
//        if(attr.getAttrGroupId()!=null){
//            BeanUtils.copyProperties(attr,relation);
//            result=result&&attrAttrGroupRelationService.saveOrUpdate(relation);
//        }
//
//        return result;
//    }

    @Override
    public AttrVo getAttrInfo(Long attrId) {

        AttrEntity attrEntity = this.getById(attrId);
        AttrAttrGroupRelationEntity relation = attrAttrGroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrGroupRelationEntity>()
                .eq(AttrAttrGroupRelationEntity::getAttrId, attrId));
        AttrVo attrVo = new AttrVo();
        if (relation != null) {
            BeanUtils.copyProperties(relation, attrVo);

        }
        BeanUtils.copyProperties(attrEntity, attrVo);


        return attrVo;
    }

//    @Override
//    @Transactional
//    public Boolean updateById(AttrVo attr) {
//        Boolean result=true;
//        AttrEntity attrEntity = new AttrEntity();
//        BeanUtils.copyProperties(attr,attrEntity);
//        result=result&&this.upodateById(attrEntity);
//        AttrAttrGroupRelationEntity relation = new AttrAttrGroupRelationEntity();
//        if(attr.getAttrGroupId()!=null){
//            BeanUtils.copyProperties(attr,relation);
//            result=result&&attrAttrGroupRelationService.saveOrUpdate(relation);
//        }
//
//        return result;
//
//    }

}