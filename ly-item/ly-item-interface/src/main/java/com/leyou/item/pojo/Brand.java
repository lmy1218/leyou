/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Brand.java
 * Author: lmy
 * Date: 19-12-6 下午9:09
 * Version: 1.0
 * LastModified: 19-12-6 下午9:09
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/6 21:09
 * @version V1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lmy
 * @ClassName 商品品牌
 * @Description TODO
 * @date 2019/12/6 21:09
 **/
@Data
@Table(name = "tb_brand")
public class Brand {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    //品牌名称
    private String name;
    //品牌图片
    private String image;
    private Character letter;

}
