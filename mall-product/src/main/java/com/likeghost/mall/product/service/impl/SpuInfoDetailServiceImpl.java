package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.SpuInfoDetailDao;
import com.likeghost.mall.product.pojo.entity.SpuInfoDetailEntity;
import com.likeghost.mall.product.service.SpuInfoDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author LikeGhost
 * @date 2023/4/11 17:03
 * @description
 */

@Service("spuInfoDetailService")
public class SpuInfoDetailServiceImpl extends ServiceImpl<SpuInfoDetailDao, SpuInfoDetailEntity> implements SpuInfoDetailService {

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<SpuInfoDetailEntity> page = this.page(
                new Query<SpuInfoDetailEntity>().getPage(params),
                new QueryWrapper<SpuInfoDetailEntity>()
        );

        return new PageVO(page);
    }

    @Override
    public boolean saveDetail(Long spuId, List<String> details) {

        if (details != null && !details.isEmpty()) {
            String detail = String.join(";", details);
            SpuInfoDetailEntity spuInfoDetail = new SpuInfoDetailEntity();
            spuInfoDetail.setDetail(detail);
            spuInfoDetail.setSpuId(spuId);
            return this.save(spuInfoDetail);
        }
        return true;
    }

}