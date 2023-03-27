package com.likeghost.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.ware.dao.WareOrderTaskDetailDao;
import com.likeghost.mall.ware.entity.WareOrderTaskDetailEntity;
import com.likeghost.mall.ware.service.WareOrderTaskDetailService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskDetailEntity> page = this.page(
                new Query<WareOrderTaskDetailEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskDetailEntity>()
        );

        return new PageVo(page);
    }

}