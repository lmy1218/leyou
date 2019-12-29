/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpecificationClient.java
 * Author: lmy
 * Date: 19-12-12 下午7:39
 * Version: 1.0
 * LastModified: 19-12-12 下午7:39
 */

package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.client
 * @date 2019/12/12 19:39
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
