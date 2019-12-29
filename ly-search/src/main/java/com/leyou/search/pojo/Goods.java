/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: Goods.java
 * Author: lmy
 * Date: 19-12-12 下午4:31
 * Version: 1.0
 * LastModified: 19-12-12 下午4:31
 */

package com.leyou.search.pojo;
/**
 * @Project leyou
 * @Package com.leyou.search.pojo
 * @author lmy
 * @date 2019/12/12 16:31
 * @version V1.0
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author lmy
 * @ClassName Goods
 * @Description TODO
 * @date 2019/12/12 16:31
 **/
@Data
@Document(indexName = "goods", type = "docs", shards = 1,replicas = 0)
public class Goods {
    //spuId
    @Id
    private Long id;
    //所有需要被搜索的信息，包含标题， 分类， 甚至品牌
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all;
    //卖点
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;
    //品牌id
    private Long brandId;
    //1级分类id
    private Long cid1;
    //2级分类id
    private Long cid2;
    //3级分类id
    private Long cid3;
    //spu创建时间
    private Date createTime;
    //价格
    private Set<Long> price;
    @Field(type = FieldType.Keyword, index = false)
    //sku信息的json结构
    private String skus;
    //可搜索的规格参数， key是参数名，值是参数值
    private Map<String, Object> specs;


}
