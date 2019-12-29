/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderStatusEnum.java
 * Author: lmy
 * Date: 19-12-26 下午9:51
 * Version: 1.0
 * LastModified: 19-12-26 下午9:51
 */

package com.leyou.order.enums;
/**
 * @Project leyou
 * @Package com.leyou.order.enums
 * @author lmy
 * @date 2019/12/26 21:51
 * @version V1.0
 */

/**
 * @author lmy
 * @ClassName OrderStatusEnum
 * @Description 订单状态
 * @date 2019/12/26 21:51
 **/

public enum OrderStatusEnum {

    UN_PAY(1, "未付款"),

    PAYED(2, "已付款"),

    DELIVERED(3, "已发货，未确认"),

    SUCCESS(4, "已确认，未评价"),

    CLOSED(5, "已关闭，交易失败"),

    RATED(6, "已评价"),

    ;
    private int code;
    private String desc;

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int value(){ return this.code; }

    public Integer getCode() {
        return this.code;
    }
}
