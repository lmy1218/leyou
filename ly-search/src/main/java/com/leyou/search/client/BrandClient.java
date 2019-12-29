/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: BrandClient.java
 * Author: lmy
 * Date: 19-12-12 下午7:37
 * Version: 1.0
 * LastModified: 19-12-12 下午7:37
 */

package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.client
 * @dater 2019/12/12 19:37
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
