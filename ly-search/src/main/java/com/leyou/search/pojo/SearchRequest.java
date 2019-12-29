/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SearchRequest.java
 * Author: lmy
 * Date: 19-12-13 下午4:48
 * Version: 1.0
 * LastModified: 19-12-13 下午4:48
 */

package com.leyou.search.pojo;
/**
 * @Project leyou
 * @Package com.leyou.search.pojo
 * @author lmy
 * @date 2019/12/13 16:48
 * @version V1.0
 */

import lombok.Data;

/**
 * @author lmy
 * @ClassName SearchRequest
 * @Description 接收搜索参数
 * @date 2019/12/13 16:48
 **/

public class SearchRequest {
    //搜索字段
    private String key;
    //当前分页
    private Integer page;
    //每页大小
    private static final  int DEFAULT_SIZE = 20;
    //默认页
    private static final Integer DEFAULT_PAGE = 1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getSize(){
        return DEFAULT_SIZE;
    }
}
