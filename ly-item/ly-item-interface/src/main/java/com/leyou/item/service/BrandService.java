/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: BrandService.java
 * Author: lmy
 * Date: 19-12-7 下午12:02
 * Version: 1.0
 * LastModified: 19-12-6 下午9:46
 */

package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.service
 * @date 2019/12/6 21:20
 */
public interface BrandService {
    /**
     * 分页查询
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    PageResult<Brand>  queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(Brand brand, List<Long> cids);

    Brand queryById(Long id);

    List<Brand> queryBrandByCid(Long cid);

    List<Brand> queryByIds(List<Long> ids);
}
