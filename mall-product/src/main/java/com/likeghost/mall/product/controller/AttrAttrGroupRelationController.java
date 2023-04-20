package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.entity.AttrAttrGroupRelationEntity;
import com.likeghost.mall.product.service.AttrAttrGroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 属性&属性分组关联
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
@RestController
@RequestMapping("product/attr-attr-group-relation")
public class AttrAttrGroupRelationController {
    @Autowired
    private AttrAttrGroupRelationService attrAttrGroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attrattrgrouprelation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageVO page = attrAttrGroupRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/list/{attrId}")
    //@RequiresPermissions("product:attrattrgrouprelation:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long attrId) {
        PageVO page = attrAttrGroupRelationService.queryPage(params, attrId);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:attrattrgrouprelation:info")
    public R info(@PathVariable("id") Long id) {
        AttrAttrGroupRelationEntity attrAttrGroupRelation = attrAttrGroupRelationService.getById(id);

        return R.ok().put("attrAttrGroupRelation", attrAttrGroupRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrattrgrouprelation:save")
    public R save(@RequestBody AttrAttrGroupRelationEntity attrAttrGroupRelation) {
        attrAttrGroupRelationService.save(attrAttrGroupRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrattrgrouprelation:update")
    public R update(@RequestBody AttrAttrGroupRelationEntity attrAttrGroupRelation) {
        attrAttrGroupRelationService.updateById(attrAttrGroupRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrattrgrouprelation:delete")
    public R delete(@RequestBody Long[] ids){
        attrAttrGroupRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
