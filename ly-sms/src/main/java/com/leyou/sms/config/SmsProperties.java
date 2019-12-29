/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SmsProperties.java
 * Author: lmy
 * Date: 19-12-20 下午4:11
 * Version: 1.0
 * LastModified: 19-12-20 下午4:11
 */

package com.leyou.sms.config;
/**
 * @Project leyou
 * @Package com.leyou.sms.config
 * @author lmy
 * @date 2019/12/20 16:11
 * @version V1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lmy
 * @ClassName SmsProperties
 * @Description TODO
 * @date 2019/12/20 16:11
 **/
@Data
@ConfigurationProperties(prefix = "ly.sms")
public class SmsProperties {
    String accessKeyId;
    String accessKeySecret;
    String signName;
    String verifyCodeTemplate;
}
