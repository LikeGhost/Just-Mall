package com.likeghost.mall.product.pojo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.likeghost.mall.product.pojo.entity.CommentReplayEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
