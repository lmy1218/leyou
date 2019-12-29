/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderDetail.java
 * Author: lmy
 * Date: 19-12-26 下午6:40
 * Version: 1.0
 * LastModified: 19-12-26 下午6:40
 */

package com.leyou.order.pojo;
/**
 * @Project leyou
 * @Package com.leyou.order.pojo
 * @author lmy
 * @date 2019/12/26 18:40
 * @version V1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lmy
 * @ClassName OrderDetail
 * @Description 订单详情实体类
 * @date 2019/12/26 18:40
 **/
@Data
@Table(name = "tb_order_detail")
public class OrderDetail {
    // 订单详情id
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    // 订单id
    private Long orderId;
    // sku商品id
    private Long skuId;
    // 购买数量
    private Integer num;
    // 商品标题
    private String title;
    // 商品动态属性键值集
    private String ownSpec;
    // 价格,单位：分
    private Long price;
    // 商品图片
    private String image;

}
