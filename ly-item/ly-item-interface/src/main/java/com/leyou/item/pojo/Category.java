/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Category.java
 * Author: lmy
 * Date: 19-12-5 下午9:43
 * Version: 1.0
 * LastModified: 19-12-5 下午9:43
 */

package com.leyou.item.pojo;
/**
 * @Project leyou
 * @Package com.leyou.item.pojo
 * @author lmy
 * @date 2019/12/5 21:43
 * @version V1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ly
 * @ClassName Category
 * @Description 商品分类
 * @date 2019/12/5 21:43
 **/
@Table(name = "tb_category")
@Data
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
}
