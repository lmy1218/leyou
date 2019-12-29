/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: FilterProperties.java
 * Author: lmy
 * Date: 19-12-24 下午7:26
 * Version: 1.0
 * LastModified: 19-12-24 下午7:26
 */

package com.leyou.gateway.config;
/**
 * @Project leyou
 * @Package com.leyou.gateway.config
 * @author lmy
 * @date 2019/12/24 19:26
 * @version V1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author lmy
 * @ClassName FilterProperties
 * @Description TODO
 * @date 2019/12/24 19:26
 **/
@Data
@ConfigurationProperties(prefix = "ly.filter")
public class FilterProperties {

    private List<String> allowPaths;
}
