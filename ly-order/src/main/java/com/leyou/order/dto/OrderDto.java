/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderDto.java
 * Author: lmy
 * Date: 19-12-26 下午7:17
 * Version: 1.0
 * LastModified: 19-12-26 下午7:17
 */

package com.leyou.order.dto;
/**
 * @Project leyou
 * @Package com.leyou.order.dto
 * @author lmy
 * @date 2019/12/26 19:17
 * @version V1.0
 */

import com.leyou.common.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lmy
 * @ClassName OrderDto
 * @Description TODO
 * @date 2019/12/26 19:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    // 收货人地址ID
    @NotNull
    private Long addressId;
    // 付款类型
    @NotNull
    private Integer paymentType;
    // 订单详情
    @NotNull
    private List<CartDto> carts;
}
