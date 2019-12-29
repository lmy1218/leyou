/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UserApi.java
 * Author: lmy
 * Date: 19-12-24 下午3:02
 * Version: 1.0
 * LastModified: 19-12-24 下午3:02
 */

package com.leyou.user.api;
/**
 * @Project leyou
 * @Package com.leyou.user.api
 * @author lmy
 * @date 2019/12/24 15:02
 * @version V1.0
 */

import com.leyou.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lmy
 * @ClassName UserApi
 * @Description TODO
 * @date 2019/12/24 15:02
 **/

public interface UserApi {

    @GetMapping("/query")
    User queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}
