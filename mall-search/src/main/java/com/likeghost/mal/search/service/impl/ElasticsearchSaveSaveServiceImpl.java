package com.likeghost.mal.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.likeghost.common.constant.EsConstant;
import com.likeghost.mal.search.pojo.dto.ProductEsDTO;
import com.likeghost.mal.search.service.ElasticsearchSaveService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/20 14:09
 * @description
 */
@Service("elasticsearchSaveService")
@Slf4j
public class ElasticsearchSaveSaveServiceImpl implements ElasticsearchSaveService {
    @Autowired
    private ElasticsearchClient esClient;

    @SneakyThrows
    @Override
    public boolean saveProducts(List<ProductEsDTO> products) {
        boolean result = true;

//        BulkResponse bulk = esClient.bulk();

        BulkRequest.Builder br = new BulkRequest.Builder();

        for (ProductEsDTO product : products) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index(EsConstant.PRODUCT_INDEX)
                            .id(product.getSkuId().toString())
                            .document(product)
                    )
            );
        }

        BulkResponse bulkResponse = esClient.bulk(br.build());

        if (bulkResponse.errors()) {
            result = false;
            log.error("Bulk had errors");
            for (BulkResponseItem item : bulkResponse.items()) {
                if (item.error() != null) {
                    log.error(item.error().reason());
                }
            }
        }


        return result;

    }
}
