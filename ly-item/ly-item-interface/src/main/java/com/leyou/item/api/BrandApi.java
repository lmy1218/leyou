/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: BrandApi.java
 * Author: lmy
 * Date: 19-12-12 下午7:35
 * Version: 1.0
 * LastModified: 19-12-12 下午7:35
 */

package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.api
 * @date 2019/12/12 19:35
 */
public interface BrandApi {

    @GetMapping("brand/{id}")
    Brand queryBrandById(@PathVariable("id") Long id);

    @GetMapping("brand/list")
    List<Brand> queryBrandsByIds(@RequestParam("ids") List<Long> ids);

}
