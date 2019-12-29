/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryClientTest.java
 * Author: lmy
 * Date: 19-12-12 下午7:03
 * Version: 1.0
 * LastModified: 19-12-12 下午7:03
 */

package com.leyou.search.client;

import com.leyou.item.pojo.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.search.client
 * @date 2019/12/12 19:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {


    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void queryCategoryByIds() {

        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(1L, 2L, 3L));
        //断言
        Assert.assertEquals(3,categories.size());
        for (Category category : categories) {
            System.out.println("category:" + category);
        }

    }
}
