/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: JwtProperties.java
 * Author: lmy
 * Date: 19-12-24 上午11:02
 * Version: 1.0
 * LastModified: 19-12-24 上午11:02
 */

package com.leyou.auth.config;
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
import java.io.File;
import java.security.PrivateKey;
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
    //登录校验的密钥
    private String secret;
    //公钥地址
    private String pubKeyPath;
    //私钥地址
    private String priKeyPath;
    //过期时间,单位分钟
    private int expire;
    //token
    private String cookieName;
    //公钥
    private PublicKey publicKey;
    //私钥
    private PrivateKey privateKey;

    //对象实例化后，读取公钥和私钥
    @PostConstruct
    public void init() throws Exception {
        //公钥和私钥不存在，先生成
        File pubPath = new File(pubKeyPath);
        File priPath = new File(priKeyPath);
        if(!pubPath.exists() || !priPath.exists()){
            //生成公匙和密匙
            RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
        }

        //读取公钥和私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}
