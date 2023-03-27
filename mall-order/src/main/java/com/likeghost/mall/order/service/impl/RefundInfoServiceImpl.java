package com.likeghost.mall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.order.dao.RefundInfoDao;
import com.likeghost.mall.order.entity.RefundInfoEntity;
import com.likeghost.mall.order.service.RefundInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<RefundInfoEntity> page = this.page(
                new Query<RefundInfoEntity>().getPage(params),
                new QueryWrapper<RefundInfoEntity>()
        );

        return new PageVo(page);
    }

}