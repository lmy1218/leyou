/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CartDto.java
 * Author: lmy
 * Date: 19-12-26 下午10:11
 * Version: 1.0
 * LastModified: 19-12-26 下午7:22
 */

package com.leyou.common.dto;
/**
 * @Project leyou
 * @Package com.leyou.order.dto
 * @author lmy
 * @date 2019/12/26 19:20
 * @version V1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmy
 * @ClassName CartDto
 * @Description TODO
 * @date 2019/12/26 19:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    // 商品skuId
    private Long skuId;
    // 购买数量
    private Integer num;
}
