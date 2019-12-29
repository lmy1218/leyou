/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Spu.java
 * Author: lmy
 * Date: 19-12-9 上午10:50
 * Version: 1.0
 * LastModified: 19-12-9 上午10:50
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/9 10:50
 * @version V1.0
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author lmy
 * @ClassName Spu
 * @Description 商品类
 * @date 2019/12/9 10:50
 **/
@Data
@Table(name = "tb_spu")
public class Spu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1;  //1级类目
    private Long cid2;  //2级类目
    private Long cid3;  //3级类目
    private String title; //标题
    private String subTitle; //子标题
    private Boolean saleable; //是否上架
    private Boolean valid; //是否有效， 逻辑删除用
    private Date createTime; //创建时间
    @JsonIgnore //忽略返回字段
    private Date LastUpdateTime; //最后修改时间

    /**---------------非数据库字段----------------*/
    @Transient
    private String cname;
    @Transient
    private String bname;

    @Transient
    private List<Sku> skus;

    @Transient
    private SpuDetail spuDetail;

}
