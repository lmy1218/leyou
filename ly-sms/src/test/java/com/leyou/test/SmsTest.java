/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SmsTest.java
 * Author: lmy
 * Date: 19-12-22 上午10:11
 * Version: 1.0
 * LastModified: 19-12-22 上午10:11
 */

package com.leyou.test;
/**
 * @Project leyou
 * @Package com.leyou.test
 * @author lmy
 * @date 2019/12/22 10:11
 * @version V1.0
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author lmy
 * @ClassName SmsTest
 * @Description TODO
 * @date 2019/12/22 10:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsTest {


    @Autowired
    private AmqpTemplate amqpTemplate;


    @Test
    public void testSend() throws InterruptedException {
        HashMap<String, String> msg = new HashMap<>();
        msg.put("phone", "18379866197");
        msg.put("code", "1314");
        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", msg);
        Thread.sleep(10000);
    }


}
