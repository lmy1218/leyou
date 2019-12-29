/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsService.java
 * Author: lmy
 * Date: 19-12-9 上午11:42
 * Version: 1.0
 * LastModified: 19-12-9 上午11:42
 */

package com.leyou.item.service;

import com.leyou.common.dto.CartDto;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item.service
 * @date 2019/12/9 11:42
 */
public interface GoodsService {
    PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);

    void saveGoods(Spu spu);

    SpuDetail queryDetailById(Long spuId);

    List<Sku> querySkuBySpuId(Long spuId);

    void updateGoods(Spu spu);

    Spu querySpuById(Long id);

    List<Sku> querySkuByIds(List<Long> ids);

    void decreaseStock(List<CartDto> carts);
}
