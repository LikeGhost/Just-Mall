package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.constant.ProductConstant;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.AttrDao;
import com.likeghost.mall.product.pojo.dao.AttrGroupDao;
import com.likeghost.mall.product.pojo.entity.*;
import com.likeghost.mall.product.pojo.vo.AttrVO;
import com.likeghost.mall.product.service.AttrAttrGroupRelationService;
import com.likeghost.mall.product.service.AttrService;
import com.likeghost.mall.product.service.CategoryService;
import com.likeghost.mall.product.service.ProductAttrValueService;
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

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Override
    public PageVO queryPageByCatId(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageVO(page);
    }

    @Override
    public PageVO queryPageByCatId(Map<String, Object> params, Long catId) {

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

        List<AttrVO> attrList = page.getRecords().stream().map((attrEntity -> {
            AttrVO attrVo = new AttrVO();
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


        PageVO pageVo = new PageVO(page);
        pageVo.setList(attrList);
        return pageVo;
    }

    @Override
    public PageVO queryPageByAttrTypeAndCatId(Map<String, Object> params, String attrType, Long catId) {

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


        PageVO pageVo = new PageVO(page);
//        pageVo.setList(attrVoList);
        return pageVo;
    }

    @Override
    public List<ProductAttrValueEntity> listBySpuId(Long spuId) {


        return productAttrValueService.list(new LambdaQueryWrapper<ProductAttrValueEntity>()
                .eq(ProductAttrValueEntity::getSpuId, spuId)
        );
    }

    @Transactional
    @Override
    public boolean updateBaseAttrValues(Long spuId, List<ProductAttrValueEntity> baseAttrValues) {
        boolean result = true;
        result = result && productAttrValueService.remove(new LambdaQueryWrapper<ProductAttrValueEntity>()
                .eq(ProductAttrValueEntity::getSpuId, spuId));

        List<ProductAttrValueEntity> collect = baseAttrValues.stream().peek(baseAttrValue -> {
            baseAttrValue.setSpuId(spuId);
        }).collect(Collectors.toList());

        result = result && productAttrValueService.saveBatch(collect);
        return result;
    }

    @Override
    @Transactional
    public Boolean saveOrUpdate(AttrVO attr) {
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
    public AttrVO getAttrInfo(Long attrId) {

        AttrEntity attrEntity = this.getById(attrId);
        AttrAttrGroupRelationEntity relation = attrAttrGroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrGroupRelationEntity>()
                .eq(AttrAttrGroupRelationEntity::getAttrId, attrId));
        AttrVO attrVo = new AttrVO();
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