/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: PageController.java
 * Author: lmy
 * Date: 19-12-15 下午11:01
 * Version: 1.0
 * LastModified: 19-12-15 下午11:01
 */

package com.leyou.page.web;
/**
 * @Project leyou
 * @Package com.leyou.page.web
 * @author lmy
 * @date 2019/12/15 23:01
 * @version V1.0
 */

import com.leyou.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author lmy
 * @ClassName PageController
 * @Description TODO
 * @date 2019/12/15 23:01
 **/
@Controller
public class PageController {

    @Autowired
    private PageService pageService;


    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") Long spuId, Model model){
        Map<String, Object> attributes = pageService.loadModel(spuId);

        model.addAllAttributes(attributes);
        return "item";
    }


}
