/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: LyUserApplication.java
 * Author: lmy
 * Date: 19-12-22 下午3:46
 * Version: 1.0
 * LastModified: 19-12-22 下午3:46
 */

package com.leyou;
/**
 * @Project leyou
 * @Package com.leyou
 * @author lmy
 * @date 2019/12/22 15:46
 * @version V1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lmy
 * @ClassName LyUserApplication
 * @Description TODO
 * @date 2019/12/22 15:46
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.user.mapper")
public class LyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyUserApplication.class, args);
    }
}
