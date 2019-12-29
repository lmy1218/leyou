/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SearchController.java
 * Author: lmy
 * Date: 19-12-13 下午4:59
 * Version: 1.0
 * LastModified: 19-12-13 下午4:59
 */

package com.leyou.search.web;
/**
 * @Project leyou
 * @Package com.leyou.search.web
 * @author lmy
 * @date 2019/12/13 16:59
 * @version V1.0
 */

import com.leyou.common.vo.PageResult;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmy
 * @ClassName SearchController
 * @Description TODO
 * @date 2019/12/13 16:59
 **/
@RestController
public class SearchController {


    @Autowired
    private SearchService searchService;


    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest request){
        return ResponseEntity.ok(searchService.search(request));
    }
}
