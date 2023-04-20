package com.likeghost.mal.search.service;

import com.likeghost.mal.search.pojo.dto.ProductEsDTO;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 14:09
 * @description
 */

public interface ElasticsearchSaveService {
    boolean saveProducts(List<ProductEsDTO> products);
}
