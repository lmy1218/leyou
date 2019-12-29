package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * 共同异常返回处理类
 */
@Data
public class ExceptionResult {
    //状态码
    private int status;
    //错误信息
    private String message;
    //时间戳
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
