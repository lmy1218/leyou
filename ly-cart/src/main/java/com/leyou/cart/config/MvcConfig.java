/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: MvcConfig.java
 * Author: lmy
 * Date: 19-12-25 下午5:20
 * Version: 1.0
 * LastModified: 19-12-25 下午5:20
 */

package com.leyou.cart.config;
/**
 * @Project leyou
 * @Package com.leyou.cart.config
 * @author lmy
 * @date 2019/12/25 17:20
 * @version V1.0
 */

import com.leyou.cart.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmy
 * @ClassName MvcConfig
 * @Description TODO
 * @date 2019/12/25 17:20
 **/
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(jwtProperties)).addPathPatterns("/**");
    }
}
