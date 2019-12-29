/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryService.java
 * Author: lmy
 * Date: 19-12-6 下午9:17
 * Version: 1.0
 * LastModified: 19-12-6 下午9:17
 */

package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.service
 * @date 2019/12/6 21:17
 */
public interface CategoryService {
    List<Category> queryCategoryByPid(Long pid);

    List<Category> queryByIds(List<Long> ids);
}
