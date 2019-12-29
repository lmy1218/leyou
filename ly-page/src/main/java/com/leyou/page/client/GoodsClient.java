/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsClient.java
 * Author: lmy
 * Date: 19-12-15 下午10:40
 * Version: 1.0
 * LastModified: 19-12-12 下午7:34
 */

package com.leyou.page.client;
/**
 * @Project leyou
 * @Package com.leyou.search.client
 * @author lmy
 * @date 2019/12/12 19:12
 * @version V1.0
 */


import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @author lmy
 * @ClassName GoodsClient
 * @Description TODO
 * @date 2019/12/12 19:12
 **/
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
