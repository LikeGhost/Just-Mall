package com.likeghost.mall.product.controller;

import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.entity.BrandEntity;
import com.likeghost.mall.product.feign.MinioService;
import com.likeghost.mall.product.service.BrandService;
import com.likeghost.mall.product.vo.BrandVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/27 22:19
 * @description
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private MinioService minioService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageVo page = brandService.queryPage(params);

        return R.ok().put("page", page);


    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        HashMap<String, String> logo = new HashMap<>();

        BrandVo brandVo = new BrandVo();
        BeanUtils.copyProperties(brand, brandVo);
        String filename = brand.getLogo();
        logo.put("name", filename);
        logo.put("url", (String) minioService.getPreSignedUrl(filename).get("preSignedUrl"));
        brandVo.setLogo(logo);
        return R.ok().put("brand", brandVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody BrandEntity brand) {
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

//    @PostMapping("/upload/{filename}")
//    public R putPreSignedUrl(@PathVariable String filename) {
//
//        String timeDir = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
//
//        // TODO: 2023/3/17 寻找合适的文件名生成方式，暂时使用UUID和简单雪花
//        String suffix = filename.split("\\.")[1];
////      String filename=new StringBuffer(String.valueOf(new Date().getTime())).reverse()+String.valueOf((int)(Math.random()*100));
//        String filepath = UUID.randomUUID().toString().replace("-", "");
//        filepath = "brand/" + timeDir + "/" + filepath + "." + suffix;
//
//        String preSignedUrl = minioService.putPreSignedUrl(filepath);
//
//        return R.ok().put("filepath", filepath).put("preSignedUrl", preSignedUrl);
//
//    }

//    @GetMapping("/file/**")
//    // TODO: 2023/3/21  应实现根据Controller的名称自动注入文件前缀,暂时重复编写
//    public R getPreSignedUrl(HttpServletRequest req) {
//
//        String uri= (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        String pattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
//        // 截取带“/”的参数
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        String filepath = antPathMatcher.extractPathWithinPattern(pattern,uri);
//
//
//        return R.ok().put("preSignedUrl",minioService.getPresignedUrl(filepath));
//    }
}
