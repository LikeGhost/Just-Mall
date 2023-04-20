package com.likeghost.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.constant.WareConstant;
import com.likeghost.common.pojo.vo.PageVO;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.ware.pojo.dao.PurchaseDao;
import com.likeghost.mall.ware.pojo.entity.PurchaseDetailEntity;
import com.likeghost.mall.ware.pojo.entity.PurchaseEntity;
import com.likeghost.mall.ware.pojo.entity.WareSkuEntity;
import com.likeghost.mall.ware.pojo.vo.CompletePurchasingVO;
import com.likeghost.mall.ware.pojo.vo.MergeItemsVO;
import com.likeghost.mall.ware.service.PurchaseDetailService;
import com.likeghost.mall.ware.service.PurchaseService;
import com.likeghost.mall.ware.service.WareSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LikeGhost
 * @date 2023/4/18 17:14
 * @description
 */

@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Autowired
    private WareSkuService wareSkuService;


    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageVO(page);
    }

    @Override
    public PageVO queryPageByConditions(Map<String, Object> params, Integer status) {
        LambdaQueryWrapper<PurchaseEntity> queryWrapper = new LambdaQueryWrapper<>();


        if (status != null) {
            queryWrapper.eq(PurchaseEntity::getStatus, status);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(q -> q.like(PurchaseEntity::getId, key)
                    .or().like(PurchaseEntity::getAssigneeId, key)
                    .or().like(PurchaseEntity::getAssigneeName, key)
                    .or().like(PurchaseEntity::getPhone, key)
                    .or().like(PurchaseEntity::getWareId, key));
        }
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                queryWrapper
        );
        return new PageVO(page);
    }

    @Override
    public PageVO queryUnreceivedPage(Map<String, Object> params) {
        LambdaQueryWrapper<PurchaseEntity> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(PurchaseEntity::getStatus, WareConstant.PurchaseDetailStatus.NEW.getCode())
                .or().eq(PurchaseEntity::getStatus, WareConstant.PurchaseDetailStatus.ASSIGNED.getCode());

        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                queryWrapper
        );
        return new PageVO(page);
    }

    @Override
    @Transactional
    public boolean mergeItems(MergeItemsVO mergeItemsVo) {


        boolean result = true;

        Long purchaseId = mergeItemsVo.getPurchaseId();
        PurchaseEntity purchaseEntity = this.getById(purchaseId);

        if (purchaseEntity == null) {
            purchaseEntity = new PurchaseEntity();
            purchaseEntity.setStatus(WareConstant.PurchaseStatus.NEW.getCode());
            purchaseEntity.setPriority(1);
            result = result && this.save(purchaseEntity);
        }
//        if(purchaseEntity.getStatus()!=WareConstant.PurchaseStatus.NEW.getCode()
//                &&purchaseEntity.getStatus()!=WareConstant.PurchaseStatus.ASSIGNED.getCode()){
//            throw new RuntimeException("选择采购单处于"+purchaseEntity.getStatus()+"状态，无法分配采购需求");
//        }


        List<Long> items = mergeItemsVo.getItems();
        List<PurchaseDetailEntity> purchaseDetails = purchaseDetailService.listByIds(items);
        purchaseDetails.forEach(purchaseDetail -> {
            if (purchaseDetail.getStatus() != WareConstant.PurchaseDetailStatus.NEW.getCode()
                    && purchaseDetail.getStatus() != WareConstant.PurchaseDetailStatus.ASSIGNED.getCode()) {

                String message = WareConstant.PurchaseDetailStatus.getValueByCode(purchaseDetail.getStatus());
                throw new RuntimeException("选择采购需求处于" + message + "状态，无法分配");

            }
        });

        List<PurchaseDetailEntity> collect = purchaseDetails.stream().peek(purchaseDetail -> {

            purchaseDetail.setStatus(WareConstant.PurchaseDetailStatus.ASSIGNED.getCode());
            purchaseDetail.setPurchaseId(mergeItemsVo.getPurchaseId());
        }).collect(Collectors.toList());
        result = result && purchaseDetailService.updateBatchById(collect);
        return result;

    }

    @Override
    @Transactional
    public boolean receivedPurchasing(List<Long> purchaseIds) {
        boolean result = true;
        List<PurchaseDetailEntity> purchaseDetails = purchaseDetailService.listByIds(purchaseIds);
        purchaseDetails.forEach(purchaseDetail -> {
            if (purchaseDetail.getStatus() == WareConstant.PurchaseDetailStatus.ASSIGNED.getCode() ||
                    purchaseDetail.getStatus() == WareConstant.PurchaseDetailStatus.NEW.getCode()) {
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatus.PURCHASING.getCode());
            }
        });
        result = result && purchaseDetailService.updateBatchById(purchaseDetails);

        List<PurchaseEntity> purchaseEntities = this.listByIds(purchaseIds);
        purchaseEntities.forEach(purchase -> {
            if (purchase.getStatus() == WareConstant.PurchaseStatus.ASSIGNED.getCode() ||
                    purchase.getStatus() == WareConstant.PurchaseStatus.NEW.getCode()) {
                purchase.setStatus(WareConstant.PurchaseStatus.RECEIVED.getCode());
            }
        });
        result = result && this.updateBatchById(purchaseEntities);
        return result;
    }

    @Override
    @Transactional
    public boolean completePurchasing(CompletePurchasingVO completePurchasingVo) {
        boolean result = true;
        boolean isFailed = false;

        List<PurchaseDetailEntity> purchaseDetails = completePurchasingVo.getPurchaseDetails();

        for (PurchaseDetailEntity purchaseDetail : purchaseDetails) {
            if (purchaseDetail.getStatus() == WareConstant.PurchaseDetailStatus.FAILED.getCode()) {
                isFailed = true;
            }
            PurchaseDetailEntity byId = purchaseDetailService.getById(purchaseDetail);
            WareSkuEntity wareSku = wareSkuService.getOne(new LambdaQueryWrapper<WareSkuEntity>()
                    .eq(WareSkuEntity::getWareId, byId.getWareId())
                    .eq(WareSkuEntity::getSkuId, byId.getSkuId()));
            if (wareSku != null) {
                wareSku.setStock(wareSku.getStock() + byId.getSkuNum());
            } else {
                wareSku = new WareSkuEntity();
                wareSku.setWareId(byId.getWareId());
                wareSku.setSkuId(byId.getSkuId());
                wareSku.setStock(byId.getSkuNum());
                wareSku.setStockLocked(0);
            }
            result = result && wareSkuService.saveOrUpdate(wareSku);
        }

        purchaseDetailService.updateBatchById(purchaseDetails);

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(completePurchasingVo.getPurchaseId());
        if (isFailed) {
            purchase.setStatus(WareConstant.PurchaseStatus.FAILED.getCode());
        } else {
            purchase.setStatus(WareConstant.PurchaseStatus.COMPLETED.getCode());
        }
        this.updateById(purchase);

        return true;
    }


}