package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.vo.AttrVo;
import com.likeghost.mall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
        PageVo page = attrService.queryPageByCatId(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/list/{catId}")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catId) {
        PageVo page = attrService.queryPageByCatId(params, catId);

        return R.ok().put("page", page);
    }

    @GetMapping("/{attrType}/list/{catId}")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable String attrType, @PathVariable Long catId) {
        PageVo page = attrService.queryPageByAttrTypeAndCatId(params, attrType, catId);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrVo attr = attrService.getAttrInfo(attrId);


        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveOrUpdate(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVo attr) {
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
