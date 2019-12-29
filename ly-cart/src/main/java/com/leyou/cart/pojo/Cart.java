/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Cart.java
 * Author: lmy
 * Date: 19-12-25 下午8:38
 * Version: 1.0
 * LastModified: 19-12-25 下午8:38
 */

package com.leyou.cart.pojo;
/**
 * @Project leyou
 * @Package com.leyou.cart.pojo
 * @author lmy
 * @date 2019/12/25 20:38
 * @version V1.0
 */

import lombok.Data;

/**
 * @author lmy
 * @ClassName Cart
 * @Description 购物车实体类
 * @date 2019/12/25 20:38
 **/
@Data
public class Cart {
    //商品id
    private Long skuId;
    //标题
    private String title;
    //图片
    private String image;
    //加入购物车时的价格
    private Long price;
    //购买数量
    private Integer num;
    //商品规格参数
    private String ownSpec;

}
