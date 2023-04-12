package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.pojo.dao.SpuInfoDao;
import com.likeghost.mall.product.pojo.entity.*;
import com.likeghost.mall.product.pojo.vo.BaseAttrValue;
import com.likeghost.mall.product.pojo.vo.Image;
import com.likeghost.mall.product.pojo.vo.SpuInfoSaveVo;
import com.likeghost.mall.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuImageService spuImagesService;

    @Autowired
    private SpuInfoDetailService spuInfoDetailService;

    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageVo(page);
    }

    @Override
    @Transactional
    public boolean save(SpuInfoSaveVo spuInfoSaveVo) {


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
        List<BaseAttrValue> baseAttrs = spuInfoSaveVo.getBaseAttrs();

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
            for (Image image : sku.getImages()) {
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

}