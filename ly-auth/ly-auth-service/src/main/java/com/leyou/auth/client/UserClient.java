/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UserClient.java
 * Author: lmy
 * Date: 19-12-24 下午3:04
 * Version: 1.0
 * LastModified: 19-12-24 下午3:04
 */

package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.auth.client
 * @date 2019/12/24 15:04
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
