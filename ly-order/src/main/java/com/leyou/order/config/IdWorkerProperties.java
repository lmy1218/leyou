/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: IdWorkerProperties.java
 * Author: lmy
 * Date: 19-12-26 下午8:31
 * Version: 1.0
 * LastModified: 19-12-26 下午8:31
 */

package com.leyou.order.config;
/**
 * @Project leyou
 * @Package com.leyou.order.config
 * @author lmy
 * @date 2019/12/26 20:31
 * @version V1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lmy
 * @ClassName IdWorkerProperties
 * @Description TODO
 * @date 2019/12/26 20:31
 **/
@Data
@ConfigurationProperties(prefix = "ly.worker")
public class IdWorkerProperties {
    // 当前机器id
    private long workerId;
    // 序列号
    private long dataCenterId;
}
