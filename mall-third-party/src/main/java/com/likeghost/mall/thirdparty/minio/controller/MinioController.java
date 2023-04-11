package com.likeghost.mall.thirdparty.minio.controller;

import com.likeghost.common.utils.R;
import com.likeghost.mall.thirdparty.minio.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @author LikeGhost
 */
@RestController
@RequestMapping("/minio")
public class MinioController {


    @Autowired
    private MinioService minioService;


    @GetMapping("/presigned_url/**")
    public R getPreSignedUrl(HttpServletRequest req) {
        String uri = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String pattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        // 截取带“/”的参数
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String filepath = antPathMatcher.extractPathWithinPattern(pattern, uri);


        return R.ok().put("preSignedUrl", minioService.getPreSignedObjectUrl(filepath));
    }

    //openfeign访问接口
//    @PostMapping("/presigned_url")
//    public String putPreSignedUrl(@RequestBody String filepath){
////        String filename = reqData.get("filename");
//        return  minioService.putPreSignedObjectUrl(filepath);
//    }


    @PostMapping("/presigned_url")
    public R putPreSignedUrl(@RequestBody Map<String, String> reqData) {

        String filename = reqData.get("filename");
        // TODO: 2023/3/17 寻找合适的文件名生成方式，暂时使用UUID或简单雪花
        String suffix = filename.split("\\.")[filename.length() - 1];
//      String filename=new StringBuffer(String.valueOf(new Date().getTime())).reverse()+String.valueOf((int)(Math.random()*100));
        String filepath = UUID.randomUUID().toString().replace("-", "");
        filepath = filepath + "." + suffix;

        return R.ok().put("filepath", filepath).put("preSignedUrl", minioService.putPreSignedObjectUrl(filepath));

    }

    @GetMapping("/endpoint")
    public R getEndpoint() {
        return R.ok().put("endpoint", minioService.getEndpoint());
    }

}
