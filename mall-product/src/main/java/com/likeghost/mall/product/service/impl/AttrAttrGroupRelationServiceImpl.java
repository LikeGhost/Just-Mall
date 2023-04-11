package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.AttrAttrGroupRelationDao;
import com.likeghost.mall.product.pojo.entity.AttrAttrGroupRelationEntity;
import com.likeghost.mall.product.service.AttrAttrGroupRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author LikeGhost
 * @date 2023/4/7 15:00
 * @description
 */
@Service("attrAttrGroupRelationService")
public class AttrAttrGroupRelationServiceImpl extends ServiceImpl<AttrAttrGroupRelationDao, AttrAttrGroupRelationEntity> implements AttrAttrGroupRelationService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<AttrAttrGroupRelationEntity> page = this.page(
                new Query<AttrAttrGroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrGroupRelationEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryPage(Map<String, Object> params, Long attrId) {

        String key = (String) params.get("key");

        LambdaQueryWrapper<AttrAttrGroupRelationEntity> queryWrapper = new LambdaQueryWrapper<AttrAttrGroupRelationEntity>();

        if (attrId != 0) {
            queryWrapper.eq(AttrAttrGroupRelationEntity::getAttrId, attrId);
        }


        IPage<AttrAttrGroupRelationEntity> page = this.page(
                new Query<AttrAttrGroupRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageVo(page);
    }

}