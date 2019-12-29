/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: LyCartApplication.java
 * Author: lmy
 * Date: 19-12-25 下午4:13
 * Version: 1.0
 * LastModified: 19-12-25 下午4:13
 */

package com.leyou;
/**
 * @Project leyou
 * @Package com.leyou
 * @author lmy
 * @date 2019/12/25 16:13
 * @version V1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lmy
 * @ClassName LyCartApplication
 * @Description 购物车启动类
 * @date 2019/12/25 16:13
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class LyCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyCartApplication.class, args);
    }
}
