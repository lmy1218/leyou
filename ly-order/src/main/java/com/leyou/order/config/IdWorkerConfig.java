/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: IdWorkerConfig.java
 * Author: lmy
 * Date: 19-12-26 下午8:37
 * Version: 1.0
 * LastModified: 19-12-26 下午8:37
 */

package com.leyou.order.config;
/**
 * @Project leyou
 * @Package com.leyou.order.config
 * @author lmy
 * @date 2019/12/26 20:37
 * @version V1.0
 */

import com.leyou.common.utils.IdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmy
 * @ClassName IdWorkerConfig
 * @Description 创建IdWorker
 * @date 2019/12/26 20:37
 **/
@Configuration
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfig {

    @Bean
    public IdWorker idWorker(IdWorkerProperties prop){
        return new IdWorker(prop.getWorkerId(), prop.getDataCenterId());
    }

}
