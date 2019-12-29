/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: User.java
 * Author: lmy
 * Date: 19-12-22 下午4:16
 * Version: 1.0
 * LastModified: 19-12-22 下午4:16
 */

package com.leyou.user.pojo;
/**
 * @Project leyou
 * @Package com.leyou.user.pojo
 * @author lmy
 * @date 2019/12/22 16:16
 * @version V1.0
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author lmy
 * @ClassName User
 * @Description TODO
 * @date 2019/12/22 16:16
 **/
@Data
@Table(name = "tb_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    //用户名
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4~32之间")
    private String username;
    //密码
    @Length(min = 4, max = 32, message = "密码长度必须在4~32之间")
    @JsonIgnore
    private String password;
    @Pattern(regexp = "/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$/", message = "手机号不正确")
    //电话
    private String phone;
    //创建时间
    private Date created;
    //密码的盐值
    @JsonIgnore
    private String salt;

}
