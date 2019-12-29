/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: NotifyController.java
 * Author: lmy
 * Date: 19-12-28 下午8:53
 * Version: 1.0
 * LastModified: 19-8-14 下午11:33
 */

package com.leyou.order.web;

import com.leyou.order.service.OrderService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NotifyController {

    @Autowired
    private OrderService orderService;


    /**
     * 微信支付回调接口
     * @param result
     * @return
     */
    @PostMapping(value = "/wxpay/notify", produces = "application/xml")
    public Map<String, String> notify(@RequestBody Map<String, String> result) {
        orderService.handleNotify(result);

        log.info("[支付回调] 接收微信支付回调, 结果:{}", result);
        Map<String, String> msg = new HashMap<>();
        msg.put("return_code", "SUCCESS");
        msg.put("return_msg", "OK");
        return msg;
    }

}
