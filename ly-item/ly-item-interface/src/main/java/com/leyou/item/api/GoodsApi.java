/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsApi.java
 * Author: lmy
 * Date: 19-12-12 下午7:33
 * Version: 1.0
 * LastModified: 19-12-12 下午7:29
 */

package com.leyou.item.api;

import com.leyou.common.dto.CartDto;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.item
 * @date 2019/12/12 19:25
 */
public interface GoodsApi {


    /**
     * 分页查询Spu
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    PageResult<Spu> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    );

    /**
     * 根据spuId查询商品描述
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    SpuDetail queryDetailById(@PathVariable("id") Long  spuId);

    /**
     * 根据spuId查询sku
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    List<Sku> querySkuById(@RequestParam("id") Long spuId);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    Spu querySpuById(@PathVariable("id") Long id);

    /**
     * 根据ids查询Sku集合
     * @param ids
     * @return
     */
    @GetMapping("sku/list/ids")
    List<Sku> querySkuByIds(@RequestParam("ids") List<Long> ids);

    /**
     * 减库存
     * @param carts
     * @return
     */
    @PostMapping("stock/decrease")
    Void decreaseStock(@RequestBody List<CartDto> carts);
}
