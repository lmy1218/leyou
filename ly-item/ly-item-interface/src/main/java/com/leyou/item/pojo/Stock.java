/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Stock.java
 * Author: lmy
 * Date: 19-12-9 下午9:30
 * Version: 1.0
 * LastModified: 19-12-9 下午9:30
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/9 21:30
 * @version V1.0
 */

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lmy
 * @ClassName Stock
 * @Description 商品库存类
 * @date 2019/12/9 21:30
 **/
@Data
@Table(name = "tb_stock")
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock; //秒杀可用库存
    private Integer seckillTotal; // 已秒杀数量
    private Integer stock; //正常库存

}
