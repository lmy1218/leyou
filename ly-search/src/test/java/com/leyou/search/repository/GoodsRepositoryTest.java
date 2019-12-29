/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsRepositoryTest.java
 * Author: lmy
 * Date: 19-12-12 下午8:06
 * Version: 1.0
 * LastModified: 19-12-12 下午8:06
 */

package com.leyou.search.repository;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.repository
 * @date 2019/12/12 20:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {


    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Test
    public void testCreateIndex(){
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }



    @Test
    public void loadData(){
        //查询spu信息
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            PageResult<Spu> result = goodsClient.querySpuByPage(page, rows, true, null);

            List<Spu> spuList = result.getItems();
            if(CollectionUtils.isEmpty(spuList)){
                break;
            }
            //构建成goods
            List<Goods> goodsList = spuList.stream().map(searchService::buildGoods).collect(Collectors.toList());

            //导入数据库索引
            goodsRepository.saveAll(goodsList);

            page++;
            size = spuList.size();
        }while (size == 100);
    }

}
