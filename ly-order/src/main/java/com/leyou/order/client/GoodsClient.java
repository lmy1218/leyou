/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsClient.java
 * Author: lmy
 * Date: 19-12-26 下午9:13
 * Version: 1.0
 * LastModified: 19-12-26 下午9:13
 */

package com.leyou.order.client;
/**
 * @Project leyou
 * @Package com.leyou.order.client
 * @author lmy
 * @date 2019/12/26 21:13
 * @version V1.0
 */

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lmy
 * @ClassName GoodsClient
 * @Description 调用item-service微服务接口
 * @date 2019/12/26 21:13
 **/
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
