package com.likeghost.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.product.pojo.entity.AttrGroupEntity;
import com.likeghost.mall.product.pojo.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(Map<String, Object> params);

    PageVo queryPage(Map<String, Object> params, Long catId);

    List<AttrGroupWithAttrsVo> getListWithAttrs(Long catId);
}

