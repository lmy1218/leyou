/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: LyUploadApplication.java
 * Author: lmy
 * Date: 19-12-7 下午4:10
 * Version: 1.0
 * LastModified: 19-12-7 下午4:10
 */

package com.leyou;
/**
 * @Project leyou
 * @Package com.leyou
 * @author lmy
 * @date 2019/12/7 16:10
 * @version V1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lmy
 * @ClassName LyUploadApplication
 * @Description 启动类
 * @date 2019/12/7 16:10
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class LyUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyUploadApplication.class);
    }
}
