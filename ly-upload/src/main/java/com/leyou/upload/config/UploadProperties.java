/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UploadProperties.java
 * Author: lmy
 * Date: 19-12-8 下午5:22
 * Version: 1.0
 * LastModified: 19-12-8 下午5:22
 */

package com.leyou.upload.config;
/**
 * @Project leyou
 * @Package com.leyou.upload.config
 * @author lmy
 * @date 2019/12/8 17:22
 * @version V1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author lmy
 * @ClassName UploadProperties
 * @Description TODO
 * @date 2019/12/8 17:22
 **/
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
