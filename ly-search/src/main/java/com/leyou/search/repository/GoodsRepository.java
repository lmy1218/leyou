/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsRepository.java
 * Author: lmy
 * Date: 19-12-12 下午7:48
 * Version: 1.0
 * LastModified: 19-12-12 下午7:47
 */

package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.repository
 * @date 2019/12/12 19:47
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
