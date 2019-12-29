/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: BrandController.java
 * Author: lmy
 * Date: 19-12-6 下午9:31
 * Version: 1.0
 * LastModified: 19-12-6 下午9:31
 */

package com.leyou.item.web;
/**
 * @Project leyou
 * @Package com.leyou.item.web
 * @author lmy
 * @date 2019/12/6 21:31
 * @version V1.0
 */

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lmy
 * @ClassName BrandController
 * @Description 商品品牌前端控制器
 * @date 2019/12/6 21:31
 **/
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandServiceImpl;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
        ){
        return ResponseEntity.ok(brandServiceImpl.queryBrandByPage(page, rows, sortBy, desc, key));
    }


    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        brandServiceImpl.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandServiceImpl.queryBrandByCid(cid));
    }


    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
        return ResponseEntity.ok(brandServiceImpl.queryById(id));
    }

    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandsByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(brandServiceImpl.queryByIds(ids));
    }

}
