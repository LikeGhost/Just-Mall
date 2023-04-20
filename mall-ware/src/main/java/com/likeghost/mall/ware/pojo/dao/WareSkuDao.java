package com.likeghost.mall.ware.pojo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.likeghost.mall.ware.pojo.dto.SkuStockDTO;
import com.likeghost.mall.ware.pojo.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {


    List<SkuStockDTO> getSkuStock(@Param("skuIds") List<Long> skuIds);
}
