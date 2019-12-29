/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: AuthService.java
 * Author: lmy
 * Date: 19-12-24 下午12:08
 * Version: 1.0
 * LastModified: 19-12-24 下午12:08
 */

package com.leyou.auth.service;
/**
 * @Project leyou
 * @Package com.leyou.auth.service
 * @author lmy
 * @date 2019/12/24 12:08
 * @version V1.0
 */

import com.leyou.auth.client.UserClient;
import com.leyou.auth.config.JwtProperties;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author lmy
 * @ClassName AuthService
 * @Description TODO
 * @date 2019/12/24 12:08
 **/
@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private UserClient userClient;


    @Autowired
    private JwtProperties jwtProperties;

    public String login(String username, String password) {

        try {
            //校验用户名和密码
            User user = userClient.queryUserByUsernameAndPassword(username, password);
            if(user == null){
                throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
            }
            //生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), username), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            log.error("[授权中心] 用户名或密码有误，用户名称:{}", username, e);
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
    }
}
