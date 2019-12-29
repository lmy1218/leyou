/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: PageService.java
 * Author: lmy
 * Date: 19-12-15 下午11:06
 * Version: 1.0
 * LastModified: 19-12-15 下午11:06
 */

package com.leyou.page.service;

import com.leyou.item.pojo.*;
import com.leyou.page.client.BrandClient;
import com.leyou.page.client.CategoryClient;
import com.leyou.page.client.GoodsClient;
import com.leyou.page.client.SpecificationClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author lmy
 * @version V1.0
 * @Project leyou
 * @Package com.leyou.page.service
 * @date 2019/12/15 23:06
 */
@Slf4j
@Service
public class PageService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private TemplateEngine templateEngine;

    public Map<String,Object> loadModel(Long spuId) {
        Map<String,Object> model = new HashMap<>();
        //查询Spu
        Spu spu = goodsClient.querySpuById(spuId);
        //查询sku集合
        List<Sku> skus = spu.getSkus();
        //查询详情
        SpuDetail detail = spu.getSpuDetail();
        //查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        // 查询商品分类
        List<Long> idList = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<Category> categories = categoryClient.queryCategoryByIds(idList);
        // 查询规格参数
        List<SpecGroup> specs = specificationClient.queryGroupListByCid(spu.getCid3());

        model.put("spu", spu);
        model.put("skus", skus);
        model.put("detail", detail);
        model.put("brand", brand);
        model.put("categories", categories);
        model.put("specs", specs);
        return model;
    }


    public void createHtml(Long spuId){
        //上下文
        Context context = new Context();
        context.setVariables(loadModel(spuId));
        //输出流
        File dest = new File("D:\\gitSpace\\springbootproject\\html", spuId + ".html");
        if(dest.exists()){
            dest.delete();
        }
        try(PrintWriter writer = new PrintWriter(dest, "UTF-8")){
            //生成html
            templateEngine.process("item", context, writer);

        }catch (Exception e){
            log.error("[静态页服务] 生成静态页异常", e);
        }
    }

    public void deleteHtml(Long spuId) {
        File dest = new File("D:\\gitSpace\\springbootproject\\html", spuId + ".html");
        if(dest.exists()){
            dest.delete();
        }
    }
}
