/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: AuthController.java
 * Author: lmy
 * Date: 19-12-24 下午12:07
 * Version: 1.0
 * LastModified: 19-12-24 下午12:07
 */

package com.leyou.auth.web;
/**
 * @Project leyou
 * @Package com.leyou.auth.web
 * @author lmy
 * @date 2019/12/24 12:07
 * @version V1.0
 */

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import com.netflix.client.http.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmy
 * @ClassName AuthController
 * @Description 授权中心
 * @date 2019/12/24 12:07
 **/
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;



    @Value("${ly.jwt.cookieName}")
    private String cookieName;

    /**
     * 登录授权功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        String token = authService.login(username, password);
        //写入Cookie
        CookieUtils.newBuilder(response).httpOnly().request(request)
                .build(cookieName, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN") String token,
                                           HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(token)) {
            //未登录
            throw new LyException(ExceptionEnum.UN_AUTHORIZED);
        }
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            //刷新token
            String newToken = JwtUtils.generateToken(info, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            CookieUtils.newBuilder(response).httpOnly().request(request)
                    .build(cookieName, newToken);

            return ResponseEntity.ok(info);
        } catch (Exception e) {
            //token已过期或者token无效
            throw new LyException(ExceptionEnum.UN_AUTHORIZED);
        }


    }



}
