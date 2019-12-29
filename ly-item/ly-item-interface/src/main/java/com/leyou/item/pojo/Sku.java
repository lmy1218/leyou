/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Sku.java
 * Author: lmy
 * Date: 19-12-9 下午9:22
 * Version: 1.0
 * LastModified: 19-12-9 下午9:22
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/9 21:22
 * @version V1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author lmy
 * @ClassName Sku
 * @Description 商品特殊信息类
 * @date 2019/12/9 21:22
 **/
@Data
@Table(name = "tb_sku")
public class Sku {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String ownSpec;//商品特殊规格的键值对
    private String indexes; //商品特殊规格的下标
    private Boolean enable; //是否有效， 逻辑删除用
    private Date createTime;
    private Date lastUpdateTime;

    /**---------非数据库字段--------*/
    @Transient
    private Integer stock; //库存

}
