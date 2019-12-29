/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: AuthFilter.java
 * Author: lmy
 * Date: 19-12-24 下午6:55
 * Version: 1.0
 * LastModified: 19-12-24 下午6:55
 */

package com.leyou.gateway.filters;
/**
 * @Project leyou
 * @Package com.leyou.gateway.filters
 * @author lmy
 * @date 2019/12/24 18:55
 * @version V1.0
 */

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lmy
 * @ClassName AuthFilter
 * @Description TODO
 * @date 2019/12/24 18:55
 **/
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class AuthFilter extends ZuulFilter {


    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        //选择前置过滤
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤优先级
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String path = request.getRequestURI();
        //判断是否要放行
        boolean isAllowPath = isAllowPath(path);

        //是否过滤
        return !isAllowPath;
    }

    /**
     * 判断当前url是否在白名单
     * @param path
     * @return
     */
    private boolean isAllowPath(String path) {
        for (String allowPath : filterProperties.getAllowPaths()) {
            if(path.startsWith(allowPath)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //获取token
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = CookieUtils.getCookieValue(request,jwtProperties.getCookieName() );
        //解析token
        try {
            UserInfo user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            //校验权限
        } catch (Exception e) {
            //未登录
            ctx.setSendZuulResponse(false);
            //返回状态码
            ctx.setResponseStatusCode(403);
        }
        return null;
    }
}
