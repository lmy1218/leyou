/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryController.java
 * Author: lmy
 * Date: 19-12-5 下午10:15
 * Version: 1.0
 * LastModified: 19-12-5 下午10:15
 */

package com.leyou.item.web;
/**
 * @Project leyou
 * @Package com.leyou.item.web
 * @author Administrator
 * @date 2019/12/5 22:15
 * @version V1.0
 */

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @ClassName CategoryController
 * @Description 商品分类处理控制器
 * @date 2019/12/5 22:15
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryServiceImpl;

    /**
     * 根据父节点ID查询商品分类
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam Long pid){
        return ResponseEntity.ok(categoryServiceImpl.queryCategoryByPid(pid));
    }

    /**
     * 根据ids查询商品分类
     * @param ids
     * @return
     */
    @GetMapping("list/ids")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(categoryServiceImpl.queryByIds(ids));
    }


}
