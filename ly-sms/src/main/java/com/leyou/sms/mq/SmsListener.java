/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SmsListener.java
 * Author: lmy
 * Date: 19-12-20 下午4:55
 * Version: 1.0
 * LastModified: 19-12-19 上午10:47
 */

package com.leyou.sms.mq;
/**
 * @Project leyou
 * @Package com.leyou.search.mq
 * @author lmy
 * @date 2019/12/19 9:30
 * @version V1.0
 */

import com.aliyuncs.exceptions.ClientException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.sms.config.SmsProperties;
import com.leyou.sms.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author lmy
 * @ClassName ItemListener
 * @Description 监听商品消息
 * @date 2019/12/19 9:30
 **/
@Slf4j
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties prop;

    /**
     * 发送短信验证码
     * @param
     */
    @RabbitListener(bindings = @QueueBinding(
            value= @Queue(name = "sms.verify.code.queue", durable = "true"),
            exchange = @Exchange(name = "ly.sms.exchange", type = ExchangeTypes.TOPIC),
            key = "sms.verify.code"
    ))
    public void listenerInsertOrUpdate(Map<String, String> msg){
        if(CollectionUtils.isEmpty(msg)){
            return;
        }
        String phone = msg.remove("phone");
        if(StringUtils.isBlank(phone)){
            return;
        }
        //处理消息
        smsUtils.sendSms(phone, prop.getSignName(), prop.getVerifyCodeTemplate(), JsonUtils.toString(msg));


    }

}
