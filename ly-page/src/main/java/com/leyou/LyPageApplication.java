/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: LyPageApplication.java
 * Author: lmy
 * Date: 19-12-15 下午8:35
 * Version: 1.0
 * LastModified: 19-12-15 下午8:35
 */

package com.leyou;
/**
 * @Project leyou
 * @Package com.leyou
 * @author lmy
 * @date 2019/12/15 20:35
 * @version V1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lmy
 * @ClassName LyPageApplication
 * @Description TODO
 * @date 2019/12/15 20:35
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LyPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyPageApplication.class, args);
    }
}
