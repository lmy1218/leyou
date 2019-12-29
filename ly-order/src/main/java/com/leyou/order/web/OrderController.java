/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderController.java
 * Author: lmy
 * Date: 19-12-26 下午7:06
 * Version: 1.0
 * LastModified: 19-12-26 下午7:06
 */

package com.leyou.order.web;
/**
 * @Project leyou
 * @Package com.leyou.order.web
 * @author lmy
 * @date 2019/12/26 19:06
 * @version V1.0
 */

import com.leyou.order.dto.OrderDto;
import com.leyou.order.pojo.Order;
import com.leyou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmy
 * @ClassName OrderController
 * @Description 订单功能
 * @date 2019/12/26 19:06
 **/
@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDto orderDto){
        //创建订单
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }


    /**
     * 根据ID查询订单
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Order> queryOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.queryOrder(id));
    }


    @GetMapping("url/{id}")
    public ResponseEntity<String> createPayUrl(@PathVariable("id") Long orderId) {
        return ResponseEntity.ok(orderService.createPayUrl(orderId));
    }

    @GetMapping("state/{id}")
    public ResponseEntity<Integer> queryOrderState(@PathVariable("id") Long orderId) {
        return ResponseEntity.ok(orderService.queryOrderState(orderId).getValue());
    }

}
