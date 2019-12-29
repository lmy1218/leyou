/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsController.java
 * Author: lmy
 * Date: 19-12-9 上午11:44
 * Version: 1.0
 * LastModified: 19-12-9 上午11:44
 */

package com.leyou.item.web;
/**
 * @Project leyou
 * @Package com.leyou.item.web
 * @author lmy
 * @date 2019/12/9 11:44
 * @version V1.0
 */

import com.leyou.common.dto.CartDto;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author lmy
 * @ClassName GoodsController
 * @Description 商品详情处理控制器
 * @date 2019/12/9 11:44
 **/
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsServiceImpl;


    /**
     * 分页查询商品spu
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ){
        return ResponseEntity.ok(goodsServiceImpl.querySpuByPage(page, rows, saleable, key));
    }


    /**
     * 商品新增
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu){
        goodsServiceImpl.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 商品修改
     * @param spu
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu){
        goodsServiceImpl.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /**
     * 根据ID查询商品描述
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long  spuId){
        return ResponseEntity.ok(goodsServiceImpl.queryDetailById(spuId));
    }


    /**
     * 根据ID查询Sku
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuById(@RequestParam("id") Long spuId){
        return ResponseEntity.ok(goodsServiceImpl.querySkuBySpuId(spuId));
    }


    /**
     * 根据sku的ID集合查询sku
     * @param ids
     * @return
     */
    @GetMapping("sku/list/ids")
    public ResponseEntity<List<Sku>> querySkuByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(goodsServiceImpl.querySkuByIds(ids));
    }



    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    public ResponseEntity<Spu> querySpuById(@PathVariable("id") Long id){
        return ResponseEntity.ok(goodsServiceImpl.querySpuById(id));
    }


    @PostMapping("stock/decrease")
    public ResponseEntity<Void> decreaseStock(@RequestBody List<CartDto> carts){
        goodsServiceImpl.decreaseStock(carts);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
