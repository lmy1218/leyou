/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SearchResult.java
 * Author: lmy
 * Date: 19-12-14 下午5:06
 * Version: 1.0
 * LastModified: 19-12-14 下午5:06
 */

package com.leyou.search.pojo;
/**
 * @Project leyou
 * @Package com.leyou.search.pojo
 * @author lmy
 * @date 2019/12/14 17:06
 * @version V1.0
 */

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author lmy
 * @ClassName SearchResult
 * @Description TODO
 * @date 2019/12/14 17:06
 **/
@Data
public class SearchResult extends PageResult<Goods> {
    //分类待选项
    private List<Category> categories;
    //品牌待选项
    private List<Brand> brands;
    //规格参数过滤条件
    private List<Map<String, Object>> specs;

    public SearchResult() {
    }

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<Category> categories, List<Brand> brands, List<Map<String, Object>> specs) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }
}
