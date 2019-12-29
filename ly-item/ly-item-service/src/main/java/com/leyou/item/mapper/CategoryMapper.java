/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryMapper.java
 * Author: lmy
 * Date: 19-12-5 下午10:03
 * Version: 1.0
 * LastModified: 19-12-5 下午10:03
 */

package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.mapper
 * @date 2019/12/5 22:03
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long> {
}
