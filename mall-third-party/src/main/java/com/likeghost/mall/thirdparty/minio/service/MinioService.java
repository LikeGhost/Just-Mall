package com.likeghost.mall.thirdparty.minio.service;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/16 15:29
 * @description
 */
public interface MinioService {


    String getPreSignedObjectUrl(String remoteFilePath);


    String putPreSignedObjectUrl(String remoteFilepath);

    String getEndpoint();
}
