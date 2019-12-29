/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderStatus.java
 * Author: lmy
 * Date: 19-12-26 下午6:47
 * Version: 1.0
 * LastModified: 19-12-26 下午6:47
 */

package com.leyou.order.pojo;
/**
 * @Project leyou
 * @Package com.leyou.order.pojo
 * @author lmy
 * @date 2019/12/26 18:47
 * @version V1.0
 */

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lmy
 * @ClassName OrderStatus
 * @Description 订单状态实体类
 * @date 2019/12/26 18:47
 **/
@Data
@Table(name = "tb_order_status")
public class OrderStatus {
    // 订单ID
    @Id
    private Long orderId;
    // 状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功
    private Integer status;
    // 订单创建时间
    private Date createTime;
    // 付款时间
    private Date paymentTime;
    // 发货时间
    private Date consignTime;
    // 交易完成时间
    private Date endTime;
    // 交易关闭时间
    private Date closeTime;
    // 评价时间
    private Date commentTime;
    

}
