package com.likeghost.mall.ware.controller;

import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.R;
import com.likeghost.mall.ware.pojo.entity.PurchaseEntity;
import com.likeghost.mall.ware.pojo.vo.CompletePurchasingVO;
import com.likeghost.mall.ware.pojo.vo.MergeItemsVO;
import com.likeghost.mall.ware.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 采购信息
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 21:01:41
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params, Integer status) {
        PageVO page = purchaseService.queryPageByConditions(params, status);

        return R.ok().put("page", page);
    }

    @GetMapping("/unreceived/list")
    public R unreceivedList(@RequestParam Map<String, Object> params) {
        PageVO page = purchaseService.queryUnreceivedPage(params);

        return R.ok().put("page", page);
    }

    @PostMapping("/merge")
    public R mergeItems(@RequestBody MergeItemsVO mergeItemsVo) {

        purchaseService.mergeItems(mergeItemsVo);

        return R.ok();
    }

    @PostMapping("/received")
    public R receivedPurchasing(@RequestBody List<Long> purchaseIds) {
        purchaseService.receivedPurchasing(purchaseIds);

        return R.ok();
    }

    @PostMapping("/complete")
    public R completePurchasing(@RequestBody CompletePurchasingVO completePurchasingVo) {
        purchaseService.completePurchasing(completePurchasingVo);

        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
