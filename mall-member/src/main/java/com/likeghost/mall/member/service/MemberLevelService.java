package com.likeghost.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:03:16
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageVo queryPage(Map<String, Object> params);
}

