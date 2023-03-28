package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.pojo.entity.CategoryEntity;
import com.likeghost.mall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 商品三级分类
 *
 * @author LikeGhost
 * @email 1154083659@qq.com
 * @date 2022-10-07 20:57:15
 */
@RestController
@RequestMapping("product/category")
@Api("商品三级分类")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageVo page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    @ApiOperation("通过id获取分类")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("新增一个商品分类")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok("添加成功");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("更新一个商品分类")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateById(category);

        return R.ok("更新成功");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除一个商品分类")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
//		categoryService.removeByIds(Arrays.asList(catIds));

        if(categoryService.removeCategoryByIds(Arrays.asList(catIds))==0){
            return R.error("删除失败");
        }
        else{
            return R.ok("删除成功");
        }
    }

    /**
     * 获取所有的标签组织而成的树形结构
     */
    @GetMapping("/list/tree")
    @ApiOperation("获得商品分类结点树")
    //@RequiresPermissions("product:category:list")
    public R listTree(){
        List<CategoryEntity>list= categoryService.listTree();

        return R.ok().put("data", list);
    }

    @PostMapping("update/batch")
    @ApiOperation("批量更新商品分类")
    public R updateBatch(@RequestBody CategoryEntity[] categoryEntities){

        boolean ret= categoryService.updateBatchById(Arrays.asList(categoryEntities));
        return R.ok("更新成功");


    }

}
