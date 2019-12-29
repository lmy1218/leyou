/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: JwtProperties.java
 * Author: lmy
 * Date: 19-12-26 上午11:31
 * Version: 1.0
 * LastModified: 19-12-25 下午4:30
 */

package com.leyou.order.config;
/**
 * @Project leyou
 * @Package com.leyou.auth.config
 * @author lmy
 * @date 2019/12/24 11:02
 * @version V1.0
 */

import com.leyou.common.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author lmy
 * @ClassName JwtProperties
 * @Description TODO
 * @date 2019/12/24 11:02
 **/
@Data
@ConfigurationProperties(prefix = "ly.jwt")
public class JwtProperties {

    private String pubKeyPath;
    //token
    private String cookieName;
    //公钥
    private PublicKey publicKey;


    //对象实例化后，读取公钥和私钥
    @PostConstruct
    public void init() throws Exception {
        //读取公钥和私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }
}
