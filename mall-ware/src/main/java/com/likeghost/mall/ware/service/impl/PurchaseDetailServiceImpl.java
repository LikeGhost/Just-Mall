package com.likeghost.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.ware.pojo.dao.PurchaseDetailDao;
import com.likeghost.mall.ware.pojo.entity.PurchaseDetailEntity;
import com.likeghost.mall.ware.service.PurchaseDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author LikeGhost
 * @date 2023/4/16 16:16
 * @description
 */
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                new QueryWrapper<PurchaseDetailEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryPageByConditions(Integer status, Long wareId, Map<String, Object> params) {

        LambdaQueryWrapper<PurchaseDetailEntity> queryWrapper = new LambdaQueryWrapper<PurchaseDetailEntity>();

        String key = (String) params.get("key");

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(q -> q.like(PurchaseDetailEntity::getPurchaseId, key)
                    .or().like(PurchaseDetailEntity::getSkuId, key));
        }

        if (status != null) {
            queryWrapper.eq(PurchaseDetailEntity::getStatus, status);
        }
        if (wareId != null) {
            queryWrapper.like(PurchaseDetailEntity::getWareId, wareId);
        }

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                queryWrapper
        );
        return new PageVo(page);
    }


}