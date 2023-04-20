package com.likeghost.mal.search.exception;

import com.likeghost.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/3/27 16:33
 * @description
 */
@RestControllerAdvice("com.likeghost.mall.product.controller")
public class GlobalExceptionHandler {


    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable e) {

        e.printStackTrace();

        return R.error().put("error", e.getMessage());
    }
}
