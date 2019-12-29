/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: UserService.java
 * Author: lmy
 * Date: 19-12-22 下午4:28
 * Version: 1.0
 * LastModified: 19-12-22 下午4:28
 */

package com.leyou.user.service;
/**
 * @Project leyou
 * @Package com.leyou.user.service
 * @author lmy
 * @date 2019/12/22 16:28
 * @version V1.0
 */

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lmy
 * @ClassName UserService
 * @Description TODO
 * @date 2019/12/22 16:28
 **/
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX= "user:verify:phone:";

    public Boolean checkData(String data, Integer type) {
        User record = new User();
        //判断type
        switch (type){
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                throw new LyException(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }
            int count = userMapper.selectCount(record);
            return count == 0;

    }

    public void sendCode(String phone) {
        String key = KEY_PREFIX + phone;
        //生成验证码
        String code = NumberUtils.generateCode(6);
        HashMap<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        //发送验证码
        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", msg);
        //保存缓存
        stringRedisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }

    public void register(User user, String code) {
        //校验验证码
        String cachCode = stringRedisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        if(!StringUtils.equals(code, cachCode)){
            throw new LyException(ExceptionEnum.INVALID_VERIFY_CODE);
        }
        //对密码加密
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        String passMd5 = CodecUtils.md5Hex(user.getPassword(), salt);
        user.setPassword(passMd5);
        //写入数据库
        user.setCreated(new Date());
        userMapper.insert(user);
    }

    public User queryUserByUsernameAndPassword(String username, String password) {
        User record = new User();
        record.setUsername(username);
        //查询
        User user = userMapper.selectOne(record);
        //校验空值
        if(user == null){
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
        //校验密码
        if(!StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(password, user.getSalt()))){
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
        user.setPassword("");
        return user;
    }
}
