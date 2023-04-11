package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.AttrGroupDao;
import com.likeghost.mall.product.pojo.entity.AttrAttrGroupRelationEntity;
import com.likeghost.mall.product.pojo.entity.AttrEntity;
import com.likeghost.mall.product.pojo.entity.AttrGroupEntity;
import com.likeghost.mall.product.pojo.vo.AttrGroupWithAttrsVo;
import com.likeghost.mall.product.service.AttrAttrGroupRelationService;
import com.likeghost.mall.product.service.AttrGroupService;
import com.likeghost.mall.product.service.AttrService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LikeGhost
 * @date 2023/4/7 15:02
 * @description
 */

@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrAttrGroupRelationService attrAttrGroupRelationService;

    @Autowired
    AttrService attrService;

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );


        return new PageVo(page);
    }

    @Override
    public PageVo queryPage(Map<String, Object> params, Long catId) {
        String key = (String) params.get("key");

        LambdaQueryWrapper<AttrGroupEntity> queryWrapper = new LambdaQueryWrapper<AttrGroupEntity>();

        if (catId != 0) {
            queryWrapper.eq(AttrGroupEntity::getCatId, catId);
        }

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((qw) -> qw.eq(AttrGroupEntity::getAttrGroupId, key)
                    .or().like(AttrGroupEntity::getAttrGroupName, "%" + key + "%"));
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );


        return new PageVo(page);

    }

    @Override
    public List<AttrGroupWithAttrsVo> getListWithAttrs(Long catId) {

        List<AttrGroupEntity> attrGroupEntityList = this.list(new LambdaQueryWrapper<AttrGroupEntity>()
                .eq(AttrGroupEntity::getCatId, catId));

        return attrGroupEntityList.stream().map(attrGroup -> {

            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVo);

            List<Long> attrIds = attrAttrGroupRelationService.list(new LambdaQueryWrapper<AttrAttrGroupRelationEntity>()
                            .eq(AttrAttrGroupRelationEntity::getAttrGroupId, attrGroup.getAttrGroupId()))
                    .stream().map(AttrAttrGroupRelationEntity::getAttrId).collect(Collectors.toList());

            List<AttrEntity> attrs = new ArrayList<>();
            if (!attrIds.isEmpty()) {
                attrs = attrService.listByIds(attrIds);

            }
            attrGroupWithAttrsVo.setAttrs(attrs);


            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());
    }

}