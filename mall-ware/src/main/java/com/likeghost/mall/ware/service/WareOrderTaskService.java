package com.likeghost.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.mall.ware.pojo.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageVO queryPage(Map<String, Object> params);
}

