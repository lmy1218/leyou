/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CartController.java
 * Author: lmy
 * Date: 19-12-25 下午8:42
 * Version: 1.0
 * LastModified: 19-12-25 下午8:42
 */

package com.leyou.cart.web;
/**
 * @Project leyou
 * @Package com.leyou.cart.web
 * @author lmy
 * @date 2019/12/25 20:42
 * @version V1.0
 */

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lmy
 * @ClassName CartController
 * @Description TODO
 * @date 2019/12/25 20:42
 **/
@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    /**
     * 新增购物车
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新购物车商品数量
     * @param id
     * @param num
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateNum(@RequestParam("id") Long id, @RequestParam("num") Integer num){
        cartService.updateNum(id, num);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 加载购物车
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Cart>> queryCartList(){
        return ResponseEntity.ok(cartService.queryCartList());
    }

    /**
     * 根据ID删除购物车商品
     * @param skuId
     * @return
     */
    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") Long skuId){
        cartService.deleteCart(skuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
