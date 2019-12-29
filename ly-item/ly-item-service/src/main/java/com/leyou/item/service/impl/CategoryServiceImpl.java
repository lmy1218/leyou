/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: CategoryServiceimpl.java
 * Author: lmy
 * Date: 19-12-6 下午9:16
 * Version: 1.0
 * LastModified: 19-12-5 下午10:34
 */

package com.leyou.item.service.impl;
/**
 * @Project leyou
 * @Package com.leyou.item.service
 * @author Administrator
 * @date 2019/12/5 22:12
 * @version V1.0
 */

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Administrator
 * @ClassName CategoryService
 * @Description 商品分类服务
 * @date 2019/12/5 22:12
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> queryCategoryByPid(Long pid) {
        //查询条件，Mapper会把对象中的非空属性作为查询条件
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        //判断结果
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return list;
    }

    public List<Category> queryByIds(List<Long> ids){
        List<Category> categories = categoryMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return categories;
    }
}
