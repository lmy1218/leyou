/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: AddressDto.java
 * Author: lmy
 * Date: 19-12-26 下午8:51
 * Version: 1.0
 * LastModified: 19-12-26 下午8:51
 */

package com.leyou.order.pojo;
/**
 * @Project leyou
 * @Package com.leyou.order.pojo
 * @author lmy
 * @date 2019/12/26 20:51
 * @version V1.0
 */

import lombok.Data;

/**
 * @author lmy
 * @ClassName AddressDto
 * @Description 收货人信息
 * @date 2019/12/26 20:51
 **/
@Data
public class AddressDto {
    private Long id;
    // 收货人姓名
    private String name;
    // 电话
    private String phone;
    // 省份
    private String state;
    // 城市
    private String city;
    // 区
    private String district;
    // 街道地址
    private String address;
    // 邮编
    private String zipCode;

    private Boolean isDefault;

}
