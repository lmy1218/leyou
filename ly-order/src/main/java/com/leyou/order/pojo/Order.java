/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Order.java
 * Author: lmy
 * Date: 19-12-26 下午6:03
 * Version: 1.0
 * LastModified: 19-12-26 下午6:03
 */

package com.leyou.order.pojo;
/**
 * @Project leyou
 * @Package com.leyou.order.pojo
 * @author lmy
 * @date 2019/12/26 18:03
 * @version V1.0
 */

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author lmy
 * @ClassName Order
 * @Description 订单实体类
 * @date 2019/12/26 18:03
 **/
@Data
@Table(name = "tb_order")
public class Order {
    // 订单id
    @Id
    private Long orderId;
    // 总金额，单位为分
    private Long totalPay;
    // 实付金额。单位:分。如:20007，表示:200元7分
    private Long actualPay;
    // 参与促销活动的id
    private String promotionIds;
    // 支付类型，1、在线支付，2、货到付款
    private Integer paymentType;
    // 邮费。单位:分。如:20007，表示:200元7分
    private Long postFee;
    // 订单创建时间
    private Date createTime;
    // 物流名称
    private String shipping_name;
    // 物流单号
    private String shipping_code;
    // 用户id
    private Long userId;
    // 买家留言
    private String buyerMessage;
    // 买家昵称
    private String buyerNick;
    // 买家是否已经评价,0未评价，1已评价
    private Boolean buyerRate;
    // 收获地址（省）
    private String receiverState;
    // 收获地址（市）
    private String receiverCity;
    // 收获地址（区/县）
    private String receiverDistrict;
    // 收获地址（街道、住址等详细地址）
    private String receiverAddress;
    // 收货人手机
    private String receiverMobile;
    // 收货人邮编
    private String receiverZip;
    // 收货人
    private String receiver;
    // 发票类型(0无发票1普通发票，2电子发票，3增值税发票)
    private Integer invoiceType;
    // 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
    private Integer sourceType;


    @Transient
    private OrderStatus orderStatus;

    @Transient
    private List<OrderDetail> orderDetails;



}
