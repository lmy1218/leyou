/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryClient.java
 * Author: lmy
 * Date: 19-12-12 下午6:48
 * Version: 1.0
 * LastModified: 19-12-12 下午6:48
 */

package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.client
 * @date 2019/12/12 18:48
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
