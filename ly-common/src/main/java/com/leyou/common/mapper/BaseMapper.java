/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: BaseMapper.java
 * Author: lmy
 * Date: 19-12-9 下午10:55
 * Version: 1.0
 * LastModified: 19-12-9 下午10:55
 */

package com.leyou.common.mapper;


import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.common.mapper
 * @date 2019/12/9 22:55
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, IdListMapper<T, Long>, InsertListMapper<T> {
}
