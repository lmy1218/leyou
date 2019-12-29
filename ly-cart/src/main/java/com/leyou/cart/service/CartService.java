/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CartService.java
 * Author: lmy
 * Date: 19-12-25 下午8:45
 * Version: 1.0
 * LastModified: 19-12-25 下午8:45
 */

package com.leyou.cart.service;
/**
 * @Project leyou
 * @Package com.leyou.cart.service
 * @author lmy
 * @date 2019/12/25 20:45
 * @version V1.0
 */

import com.leyou.cart.interceptor.UserInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lmy
 * @ClassName CartService
 * @Description TODO
 * @date 2019/12/25 20:45
 **/
@Service
public class CartService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "cart:uid:";


    /**
     * 添加购物车
     * @param cart
     */
    public void addCart(Cart cart) {
        //获取当前用户
        UserInfo user = UserInterceptor.getUser();
        //key
        String key =KEY_PREFIX + user.getId();
        //hashKey
        String hashKey = cart.getSkuId().toString();
        //记录num
        Integer num = cart.getNum();
        BoundHashOperations<String, Object, Object> operations = stringRedisTemplate.boundHashOps(key);
        //判断当前商品是否存在
        if (operations.hasKey(hashKey)) {
            //存在，修改数量
            String json = operations.get(hashKey).toString();
            cart = JsonUtils.toBean(json, Cart.class);
            cart.setNum(cart.getNum() + num);
        }
        //存入redis
        operations.put(hashKey, JsonUtils.toString(cart));
    }


    /**
     * 查询购物车
     * @return
     */
    public List<Cart> queryCartList() {
        //获取当前用户
        UserInfo user = UserInterceptor.getUser();
        //key
        String key =KEY_PREFIX + user.getId();
        if(!stringRedisTemplate.hasKey(key)){
            //key不存在
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        BoundHashOperations<String, Object, Object> operations = stringRedisTemplate.boundHashOps(key);
        List<Cart> cartList = operations.values().stream().map(o -> JsonUtils.toBean(o.toString(), Cart.class)).collect(Collectors.toList());

        return cartList;
    }


    /**
     * 修改购物车数量
     * @param id
     * @param num
     */
    public void updateNum(Long id, Integer num) {
        //获取当前用户
        UserInfo user = UserInterceptor.getUser();
        //key
        String key =KEY_PREFIX + user.getId();
        //hashKey
        String hashKey = id.toString();

        BoundHashOperations<String, Object, Object> operations = stringRedisTemplate.boundHashOps(key);
        //判断当前商品是否存在
        if (!operations.hasKey(hashKey)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        //存在，修改数量
        String json = operations.get(hashKey).toString();
        Cart cart = JsonUtils.toBean(json, Cart.class);
        cart.setNum(num);
        //存入redis
        operations.put(hashKey, JsonUtils.toString(cart));

    }

    public void deleteCart(Long skuId) {
        //获取当前用户
        UserInfo user = UserInterceptor.getUser();
        //key
        String key =KEY_PREFIX + user.getId();
        //hashKey
        String hashKey = skuId.toString();
        //判断当前商品是否存在
        if (!stringRedisTemplate.opsForHash().hasKey(key, hashKey)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        //删除当前商品
        stringRedisTemplate.opsForHash().delete(key, hashKey);

    }
}
