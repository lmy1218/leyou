/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: LyOrderApplication.java
 * Author: lmy
 * Date: 19-12-26 上午11:26
 * Version: 1.0
 * LastModified: 19-12-26 上午11:26
 */

package com.leyou;
/**
 * @Project leyou
 * @Package com.leyou
 * @author lmy
 * @date 2019/12/26 11:26
 * @version V1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lmy
 * @ClassName LyOrderApplication
 * @Description 订单服务启动类
 * @date 2019/12/26 11:26
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.leyou.order.mapper")
public class LyOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyOrderApplication.class, args);
    }
}
