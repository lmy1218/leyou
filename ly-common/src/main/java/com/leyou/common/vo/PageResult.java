/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: PageResult.java
 * Author: lmy
 * Date: 19-12-6 下午9:27
 * Version: 1.0
 * LastModified: 19-12-6 下午9:27
 */

package com.leyou.common.vo;
/**
 * @Project leyou
 * @Package com.leyou.common.vo
 * @author lmy
 * @date 2019/12/6 21:27
 * @version V1.0
 */

import lombok.Data;

import java.util.List;

/**
 * @author lmy
 * @ClassName PageResult
 * @Description 分页查询返回值
 * @date 2019/12/6 21:27
 **/
@Data
public class PageResult<T> {
    //总条数
    private Long total;
    //总页数
    private Integer totalPage;
    //当前页面数据
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
