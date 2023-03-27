package com.likeghost.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.mall.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageVo queryPage(Map<String, Object> params);
}

