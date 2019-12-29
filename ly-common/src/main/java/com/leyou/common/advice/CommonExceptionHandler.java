/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CommonExceptionHandler.java
 * Author: lmy
 * Date: 19-12-5 下午4:56
 * Version: 1.0
 * LastModified: 19-12-5 下午4:56
 */

package com.leyou.common.advice;
/**
 * @Project leyou
 * @Package com.leyou.common.advice
 * @author Administrator
 * @date 2019/12/5 16:56
 * @version V1.0
 */

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lmy
 * @ClassName CommonExceptionHandler
 * @Description 共通异常
 * @date 2019/12/5 16:56
 **/

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }

}
