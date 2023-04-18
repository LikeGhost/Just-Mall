package com.likeghost.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.ware.pojo.dao.UndoLogDao;
import com.likeghost.mall.ware.pojo.entity.UndoLogEntity;
import com.likeghost.mall.ware.service.UndoLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author LikeGhost
 * @date 2023/4/18 17:14
 * @description
 */

@Service("undoLogService")
public class UndoLogServiceImpl extends ServiceImpl<UndoLogDao, UndoLogEntity> implements UndoLogService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<UndoLogEntity> page = this.page(
                new Query<UndoLogEntity>().getPage(params),
                new QueryWrapper<UndoLogEntity>()
        );

        return new PageVo(page);
    }

}