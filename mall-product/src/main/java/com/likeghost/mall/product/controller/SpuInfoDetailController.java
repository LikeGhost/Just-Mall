package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.entity.SpuInfoDetailEntity;
import com.likeghost.mall.product.service.SpuInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * spu信息介绍
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
@RestController
@RequestMapping("product/spu-info-detail")
public class SpuInfoDetailController {
    @Autowired
    private SpuInfoDetailService spuInfoDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:spu-info-detail:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageVo page = spuInfoDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{spuId}")
    //@RequiresPermissions("product:spu-info-detail:info")
    public R info(@PathVariable("spuId") Long spuId){
        SpuInfoDetailEntity spuInfoDetail = spuInfoDetailService.getById(spuId);

        return R.ok().put("spuInfoDetail", spuInfoDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:spu-info-detail:save")
    public R save(@RequestBody SpuInfoDetailEntity spuInfoDetail) {
        spuInfoDetailService.save(spuInfoDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:spu-info-detail:update")
    public R update(@RequestBody SpuInfoDetailEntity spuInfoDetail) {
        spuInfoDetailService.updateById(spuInfoDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spu-info-detail:delete")
    public R delete(@RequestBody Long[] spuIds){
        spuInfoDetailService.removeByIds(Arrays.asList(spuIds));

        return R.ok();
    }

}
