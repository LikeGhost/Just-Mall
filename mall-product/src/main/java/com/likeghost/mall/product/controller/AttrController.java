package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.entity.ProductAttrValueEntity;
import com.likeghost.mall.product.pojo.vo.AttrVO;
import com.likeghost.mall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 商品属性
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageVO page = attrService.queryPageByCatId(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/list/{catId}")
    //@RequiresPermissions("product:attr:list")
    public R listByCatId(@RequestParam Map<String, Object> params, @PathVariable Long catId) {
        PageVO page = attrService.queryPageByCatId(params, catId);

        return R.ok().put("page", page);
    }

    @GetMapping("/{attrType}/list/{catId}")
    //@RequiresPermissions("product:attr:list")
    public R listByAttrTypeAndCatId(@RequestParam Map<String, Object> params, @PathVariable String attrType, @PathVariable Long catId) {
        PageVO page = attrService.queryPageByAttrTypeAndCatId(params, attrType, catId);

        return R.ok().put("page", page);
    }

    @GetMapping("/list/spu/{spuId}")
    public R listBySpuId(@PathVariable Long spuId) {
        List<ProductAttrValueEntity> data = attrService.listBySpuId(spuId);

        return R.ok().put("data", data);
    }

    @PostMapping("/update/{spuId}")
    public R updateBaseAttrValues(@PathVariable Long spuId, @RequestBody List<ProductAttrValueEntity> baseAttrValues) {
        attrService.updateBaseAttrValues(spuId, baseAttrValues);

        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrVO attr = attrService.getAttrInfo(attrId);


        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVO attr) {
        attrService.saveOrUpdate(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVO attr) {
        attrService.saveOrUpdate(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
