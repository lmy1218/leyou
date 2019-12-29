/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UserController.java
 * Author: lmy
 * Date: 19-12-22 下午4:29
 * Version: 1.0
 * LastModified: 19-12-22 下午4:29
 */

package com.leyou.user.web;
/**
 * @Project leyou
 * @Package com.leyou.user.web
 * @author lmy
 * @date 2019/12/22 16:29
 * @version V1.0
 */

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lmy
 * @ClassName UserController
 * @Description TODO
 * @date 2019/12/22 16:29
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验数据
     * @param data
     * @param type
     * @return
     */
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkData(@PathVariable("data") String data,
                                             @PathVariable("type") Integer type){
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    /**
     * 发送验证码功能
     * @param phone
     * @return
     */
    @PostMapping("code")
    public ResponseEntity<Void> sendCode(@RequestParam("phone") String phone){
        userService.sendCode(phone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 注册功能
     * @param user
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code){
        userService.register(user, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/query")
    public ResponseEntity<User> queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        return ResponseEntity.ok(userService.queryUserByUsernameAndPassword(username, password));
    }

}
