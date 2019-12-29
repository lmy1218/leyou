/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: ItemListener.java
 * Author: lmy
 * Date: 19-12-19 上午9:30
 * Version: 1.0
 * LastModified: 19-12-19 上午9:30
 */

package com.leyou.search.mq;
/**
 * @Project leyou
 * @Package com.leyou.search.mq
 * @author lmy
 * @date 2019/12/19 9:30
 * @version V1.0
 */

import com.leyou.search.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lmy
 * @ClassName ItemListener
 * @Description 监听商品消息
 * @date 2019/12/19 9:30
 **/
@Component
public class ItemListener {

    @Autowired
    private SearchService searchService;


    @RabbitListener(bindings = @QueueBinding(
            value= @Queue(name = "search.item.insert.queue", durable = "true"),
            exchange = @Exchange(name = "ly.item.exchange", type = ExchangeTypes.TOPIC),
            key = {"item.insert", "item.update"}
    ))
    public void listenerInsertOrUpdate(Long spuId){
        if(spuId == null){
            return;
        }
        //处理消息
        searchService.createOrUpdateIndex(spuId);
    }


    @RabbitListener(bindings = @QueueBinding(
            value= @Queue(name = "search.item.delete.queue", durable = "true"),
            exchange = @Exchange(name = "ly.item.exchange", type = ExchangeTypes.TOPIC),
            key = {"item.delete"}
    ))
    public void listenerDelete(Long spuId){
        if(spuId == null){
            return;
        }
        //处理消息
        searchService.deleteIndex(spuId);
    }
}
