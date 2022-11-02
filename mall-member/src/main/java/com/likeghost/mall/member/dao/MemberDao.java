package com.likeghost.mall.member.dao;

import com.likeghost.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:03:16
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
