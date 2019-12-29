/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryClient.java
 * Author: lmy
 * Date: 19-12-15 下午10:40
 * Version: 1.0
 * LastModified: 19-12-12 下午7:36
 */

package com.leyou.page.client;

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
