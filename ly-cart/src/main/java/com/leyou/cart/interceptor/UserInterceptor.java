/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UserInterceptor.java
 * Author: lmy
 * Date: 19-12-25 下午4:31
 * Version: 1.0
 * LastModified: 19-12-25 下午4:31
 */

package com.leyou.cart.interceptor;
/**
 * @Project leyou
 * @Package com.leyou.cart.interceptor
 * @author lmy
 * @date 2019/12/25 16:31
 * @version V1.0
 */

import com.leyou.cart.config.JwtProperties;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmy
 * @ClassName UserInterceptor
 * @Description 用户信息拦截器
 * @date 2019/12/25 16:31
 **/
@Slf4j
public class UserInterceptor implements HandlerInterceptor {


    private JwtProperties jwtProperties;

    private static final ThreadLocal<UserInfo> tl = new ThreadLocal<>();

    public UserInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取Cookie中的token
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        //解析token
        try {
            UserInfo user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            //存入线程域
            tl.set(user);
            return true;
        } catch (Exception e) {
            log.error("[购物车服务] 解析用户身份失败.", e);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //清空线程域
        tl.remove();
    }

    //提供外部获取线程中的user信息
    public static UserInfo getUser(){
        return tl.get();
    }
}
