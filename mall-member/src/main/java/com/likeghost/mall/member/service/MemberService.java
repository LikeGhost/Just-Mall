package com.likeghost.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.utils.PageUtils;
import com.likeghost.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:03:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

