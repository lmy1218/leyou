/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SpuDetail.java
 * Author: lmy
 * Date: 19-12-9 上午11:33
 * Version: 1.0
 * LastModified: 19-12-9 上午11:33
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/9 11:33
 * @version V1.0
 */

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lmy
 * @ClassName SpuDetail
 * @Description TODO
 * @date 2019/12/9 11:33
 **/
@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId; //对应的spu的id
    private String description; //商品描述
    private String genericSpec; //商品特殊规格的名称及可选值的模版
    private String specialSpec; //商品的全局规格属性
    private String packingList; //包装清单
    private String afterService; //售后服务
}
