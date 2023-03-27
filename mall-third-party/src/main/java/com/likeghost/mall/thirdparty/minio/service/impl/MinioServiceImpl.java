package com.likeghost.mall.thirdparty.minio.service.impl;

import com.likeghost.mall.thirdparty.minio.service.MinioService;
import com.likeghost.mall.thirdparty.minio.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/16 15:26
 * @description
 */
@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public String putPreSignedObjectUrl(String remoteFilepath) {

        return minioUtil.putPreSignedObjectUrl(remoteFilepath);
    }

    @Override
    public String getEndpoint() {
        return minioUtil.getEndpoint();
    }

    @Override
    public String getPreSignedObjectUrl(String remoteFilePath) {

        return minioUtil.getPreSignedObjectUrl(remoteFilePath);
    }
}
