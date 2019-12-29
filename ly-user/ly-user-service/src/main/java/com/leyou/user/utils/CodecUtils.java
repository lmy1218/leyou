/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CodecUtils.java
 * Author: lmy
 * Date: 19-12-23 上午9:42
 * Version: 1.0
 * LastModified: 19-6-19 上午11:40
 */

package com.leyou.user.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @Author: 98050
 * @Time: 2018-10-23 10:49
 * @Feature: 密码加密
 */
public class CodecUtils {

    public static String md5Hex(String data, String salt){
        if(!StringUtils.isBlank(salt)){
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }


    public static String shaHex(String data, String salt){
        if(!StringUtils.isBlank(salt)){
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }

    public static String generateSalt(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }


}
