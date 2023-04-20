package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.constant.ProductConstant;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.common.utils.R;
import com.likeghost.mall.product.feign.ElasticsearchSaveFeignService;
import com.likeghost.mall.product.feign.WareSkuFeignService;
import com.likeghost.mall.product.pojo.bo.SaleAttrValue;
import com.likeghost.mall.product.pojo.dao.SpuInfoDao;
import com.likeghost.mall.product.pojo.dto.ProductEsDTO;
import com.likeghost.mall.product.pojo.dto.SkuStockDTO;
import com.likeghost.mall.product.pojo.entity.*;
import com.likeghost.mall.product.pojo.vo.SkuSaveVO;
import com.likeghost.mall.product.pojo.vo.SpuInfoSaveVO;
import com.likeghost.mall.product.service.*;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LikeGhost
 * @date 2023/4/14 21:09
 * @description
 */
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuImageService spuImagesService;

    @Autowired
    private SpuInfoDetailService spuInfoDetailService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;


    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WareSkuFeignService wareSkuFeignService;

    @Autowired
    private ElasticsearchSaveFeignService elasticsearchSaveFeignService;

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageVO(page);
    }

    @Override
    @Transactional
    public boolean save(SpuInfoSaveVO spuInfoSaveVo) {


        //保存数据至pms_spu_info
        SpuInfoEntity spuInfo = new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfoSaveVo, spuInfo);
        this.save(spuInfo);

        //保存数据至pms_spu_images
        List<String> images = spuInfoSaveVo.getImages();
        spuImagesService.saveImages(spuInfo.getId(), images);

        //保存数据至pms_spu_info_detail
        List<String> detail = spuInfoSaveVo.getDetail();
        spuInfoDetailService.saveDetail(spuInfo.getId(), detail);

        //保存数据至pms_product_attr_value
        // TODO: 2023/4/11 性能应该需要优化
        List<SpuInfoSaveVO.BaseAttrValue> baseAttrs = spuInfoSaveVo.getBaseAttrs();

        List<ProductAttrValueEntity> productAttrValues = baseAttrs.stream().map(item -> {
            ProductAttrValueEntity productAttrValue = new ProductAttrValueEntity();
            BeanUtils.copyProperties(item, productAttrValue);
//            AttrEntity attr = attrService.getById(productAttrValue.getAttrId());
//            productAttrValue.setAttrName(attr.getAttrName());
            productAttrValue.setSpuId(spuInfo.getId());
            return productAttrValue;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(productAttrValues);

        //保存数据至pms_sku_info
        spuInfoSaveVo.getSkus().forEach(sku -> {

            SkuInfoEntity skuInfo = new SkuInfoEntity();
            BeanUtils.copyProperties(sku, skuInfo);

            BeanUtils.copyProperties(spuInfo, skuInfo);
            skuInfo.setSpuId(spuInfo.getId());
            skuInfo.setSaleCount(0L);
            for (SkuSaveVO.Image image : sku.getImages()) {
                if (image.getDefaultImg() == 1) {
                    skuInfo.setSkuDefaultImg(image.getImgUrl());
                }
            }
            skuInfoService.save(skuInfo);
            //保存数据至pms_sku_image
            List<SkuImageEntity> skuImages = sku.getImages().stream().map(image -> {
                SkuImageEntity skuImage = new SkuImageEntity();
                BeanUtils.copyProperties(image, skuImage);
                skuImage.setSkuId(skuInfo.getSkuId());
                return skuImage;
            }).collect(Collectors.toList());
            skuImageService.saveBatch(skuImages);
            //保存数据至pms_sku_sale_attr_value
            List<SkuSaleAttrValueEntity> skuSaleAttrValues = sku.getSaleAttrs().stream().map(saleAttrValue -> {
                SkuSaleAttrValueEntity skuSaleAttrValue = new SkuSaleAttrValueEntity();
                skuSaleAttrValue.setSkuId(skuInfo.getSkuId());
                BeanUtils.copyProperties(saleAttrValue, skuSaleAttrValue);
//                AttrEntity attr = attrService.getById(saleAttrValue.getAttrId());
//                skuSaleAttrValue.setAttrId(attr.getAttrId());
//                saleAttrValue.get
                return skuSaleAttrValue;

            }).collect(Collectors.toList());

            skuSaleAttrValueService.saveBatch(skuSaleAttrValues);


//
        });


        return true;
    }

    @SneakyThrows
    @Override
    public PageVO queryPageByConditions(Long brandId, Long catId, Integer publishStatus, Map<String, Object> params) {
        LambdaQueryWrapper<SpuInfoEntity> queryWrapper = new LambdaQueryWrapper<>();


        if (brandId != null) {
            queryWrapper.eq(SpuInfoEntity::getBrandId, brandId);
        }
        if (catId != null && catId != 0) {
            queryWrapper.eq(SpuInfoEntity::getCatId, catId);
        }

        if (publishStatus != null) {
            queryWrapper.eq(SpuInfoEntity::getPublishStatus, publishStatus);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(q -> q.like(SpuInfoEntity::getId, key)
                    .or().like(SpuInfoEntity::getSpuName, key)
                    .or().like(SpuInfoEntity::getSpuDescription, key));
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageVO(page);

    }

    @Override
    public boolean putSpu(Long spuId) {

        List<SkuInfoEntity> skus = skuInfoService.list(new LambdaQueryWrapper<SkuInfoEntity>()
                .eq(SkuInfoEntity::getSpuId, spuId));

        if (skus != null && !skus.isEmpty()) {
            Map<Long, SkuInfoEntity> skuMap = skus.stream().collect(Collectors.toMap(SkuInfoEntity::getSkuId, sku -> sku));

            List<SkuSaleAttrValueEntity> skuSaleAttrValues = skuSaleAttrValueService.list(new LambdaQueryWrapper<SkuSaleAttrValueEntity>().in(SkuSaleAttrValueEntity::getSkuId, skuMap.keySet()));

            //将属性根据skuId分组聚合
            Map<Long, List<SkuSaleAttrValueEntity>> skuSaleValueMap = skuSaleAttrValues.stream().collect(Collectors.groupingBy(SkuSaleAttrValueEntity::getSkuId));


            SpuInfoEntity spu = this.getById(spuId);

            BrandEntity brand = brandService.getById(spu.getBrandId());
            CategoryEntity category = categoryService.getById(spu.getCatId());


            List<SkuStockDTO> skuStocks = wareSkuFeignService.getSkuStock(new ArrayList<>(skuMap.keySet()));

            Map<Long, Boolean> stuStockMap = skuStocks.stream().collect(Collectors.toMap(SkuStockDTO::getSkuId, stock -> stock.getStock() > 0));

            //构建es数据模型
            List<ProductEsDTO> products = skus.stream().map(sku -> {
                ProductEsDTO productEsDto = new ProductEsDTO();
                BeanUtils.copyProperties(sku, productEsDto);

                List<SaleAttrValue> saleAttrValues = skuSaleValueMap.get(sku.getSkuId()).stream().map(skuSaleValue -> {
                    SaleAttrValue saleAttrValue = new SaleAttrValue();
                    BeanUtils.copyProperties(skuSaleValue, saleAttrValue);
                    return saleAttrValue;
                }).collect(Collectors.toList());

                productEsDto.setAttrs(saleAttrValues);
                productEsDto.setSkuImg(sku.getSkuDefaultImg());
                productEsDto.setSkuPrice(sku.getPrice());
                productEsDto.setBrandName(brand.getName());
                productEsDto.setBrandImg(brand.getLogo());
                productEsDto.setCategoryName(category.getName());
                productEsDto.setHotScore(0L);
                Boolean hasStock = stuStockMap.get(sku.getSkuId());
                productEsDto.setHasStock(hasStock != null && hasStock);

                return productEsDto;
            }).collect(Collectors.toList());

            R r = elasticsearchSaveFeignService.saveProduct(products);
            if (r.getCode() == 0) {

                spu.setPublishStatus(ProductConstant.PublishStatus.ON_SALE.getCode());
                this.updateById(spu);
            } else {

            }


            return false;

        }


        return false;
    }

}