package com.likeghost.mal.search.controller;

import com.likeghost.common.utils.R;
import com.likeghost.mal.search.pojo.dto.ProductEsDTO;
import com.likeghost.mal.search.service.ElasticsearchSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 13:52
 * @description
 */
@RestController
@RequestMapping("search/save")
@Slf4j
public class ElasticsearchSaveController {

    @Autowired
    private ElasticsearchSaveService elasticsearchSaveService;

    @PostMapping("/product")
    public R saveProduct(@RequestBody List<ProductEsDTO> products) {

        boolean result;

        try {
            result = elasticsearchSaveService.saveProducts(products);
        } catch (Exception e) {
            log.error("ES保存商品错误{}", e);
            return R.error();
        }


        if (result) {
            return R.ok();
        } else {
            return R.error();
        }


    }
}
