/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: PageServiceTest.java
 * Author: lmy
 * Date: 19-12-17 下午8:26
 * Version: 1.0
 * LastModified: 19-12-17 下午8:26
 */

package com.leyou.page.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.page.service
 * @date 2019/12/17 20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageServiceTest {

    @Autowired
    private PageService pageService;

    @Test
    public void createHtml() {
        pageService.createHtml(204L);
    }
}
