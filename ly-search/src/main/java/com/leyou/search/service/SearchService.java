/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: SearchService.java
 * Author: lmy
 * Date: 19-12-12 下午9:21
 * Version: 1.0
 * LastModified: 19-12-12 下午9:21
 */

package com.leyou.search.service;
/**
 * @Project leyou
 * @Package com.leyou.search.service
 * @author lmy
 * @date 2019/12/12 21:21
 * @version V1.0
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.*;
import com.leyou.search.client.BrandClient;
import com.leyou.search.client.CategoryClient;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.client.SpecificationClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;
import com.leyou.search.repository.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lmy
 * @ClassName SearchService
 * @Description 搜索服务
 * @date 2019/12/12 21:21
 **/
@Slf4j
@Service
public class SearchService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate template;



    public Goods buildGoods(Spu spu){
        Long spuId = spu.getId();
        //查询分类
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if(CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        //查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        if(brand == null){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //搜索字段
        String all = spu.getSubTitle() + StringUtils.join(names, " ") + brand.getName();

        //查询Sku
        List<Sku> skuList = goodsClient.querySkuById(spu.getId());
        if(CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        //处理Sku
        List<Map<String, Object>> skus = new ArrayList<>();
        Set<Long> priceSet = new HashSet<>();
        for (Sku sku : skuList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", sku.getId());
            map.put("title", sku.getTitle());
            map.put("price", sku.getPrice());
            map.put("images", StringUtils.substringBefore(sku.getImages(), ","));
            skus.add(map);
            priceSet.add(sku.getPrice());
        }
        //查询规格参数
        List<SpecParam> params = specificationClient.queryParamList(null, spu.getCid3(), true);
        if(CollectionUtils.isEmpty(params)){
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        //查询商品详情
        SpuDetail spuDetail = goodsClient.queryDetailById(spuId);
        if(spuDetail == null){
            throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }
        //通用规格参数
        Map<Long, String> genericSpec = JsonUtils.toMap(spuDetail.getGenericSpec(), Long.class, String.class);
        //特有规格参数
        Map<Long, List<String>> specialSpec = JsonUtils.nativeRead(spuDetail.getSpecialSpec(), new TypeReference<Map<Long, List<String>>>() {
        });

        //查询spec
        Map<String, Object> specs = new HashMap<>();
        for (SpecParam param : params) {
            String key = param.getName();
            Object value = "";
            if(param.getGeneric()){
                value = genericSpec.get(param.getId());
               //判断是否是数字类型
                if(param.getNumeric()){
                    value = chooseSegment(value.toString(), param);
                }
            }else {
                value = specialSpec.get(param.getId());
            }
            specs.put(key, value);
        }


        //构建Goods对象
        Goods goods = new Goods();
        goods.setBrandId(spu.getBrandId());
        goods.setCid2(spu.getCid2());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setId(spu.getId());
        // TODO 搜索字段
        goods.setAll(all);
        // TODO 所有价格的集合
        goods.setPrice(priceSet);
        // TODO 所有sku的集合
        goods.setSkus(JsonUtils.toString(skus));
        // TODO 所有可搜索的规格参数
        goods.setSpecs(specs);
        goods.setSubTitle(spu.getSubTitle());

        return goods;
    }


    private String chooseSegment(String value, SpecParam p){
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        //保存数据段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            //获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if(segs.length == 2){
                end = NumberUtils.toDouble(segs[1]);
            }
            //判断是否在范围内
            if(val >= begin && val <end){
                if (segs.length == 1){
                    result = segs[0] + p.getUnit() + "以上";
                }else if(begin == 0){
                    result = segs[1] + p.getUnit() + "以下";
                }else{
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }

    public PageResult<Goods> search(SearchRequest request) {
        String key = request.getKey();
        int page = request.getPage() - 1;
        int size = request.getSize();
        //创建查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //结果字段过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[] {"id", "subTitle", "skus"}, null));
        //分页
        queryBuilder.withPageable(PageRequest.of(page, size));
        //搜索条件
        QueryBuilder basicQuery = QueryBuilders.matchQuery("all", key);
        //过滤
        queryBuilder.withQuery(basicQuery);
        //聚合分类和品牌
        String categoryAggName = "category_agg";
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));
        String brandAggName = "brand_agg";
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandId"));

        //查询
        AggregatedPage<Goods> pageResult = template.queryForPage(queryBuilder.build(), Goods.class);
        //解析结果
        long total = pageResult.getTotalElements();
        int totalPage = pageResult.getTotalPages();
        List<Goods> goodsList = pageResult.getContent();
        //解析聚合结果
        Aggregations aggs = pageResult.getAggregations();
        List<Category> categories = parseCategoryAgg(aggs.get(categoryAggName));
        List<Brand> brands = parseBrandAgg(aggs.get(brandAggName));
        //完成规格参数聚合
        List<Map<String, Object>> specs = null;
        if(categories != null && categories.size() == 1){
            specs = buildSpecificationAgg(categories.get(0).getId(), basicQuery);
        }

        return new SearchResult(total, totalPage, goodsList, categories, brands, specs);
    }

    private List<Map<String,Object>> buildSpecificationAgg(Long cid, QueryBuilder basicQuery) {
        List<Map<String, Object>> specs = new ArrayList<>();
        //查询聚合的规格参数
        List<SpecParam> params = specificationClient.queryParamList(null, cid, true);
        //聚合
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(basicQuery);
        for (SpecParam param : params) {
            String name = param.getName();
            queryBuilder.addAggregation(AggregationBuilders.terms(name).field("specs."+name+".keyword"));
        }
        //获取结果
        AggregatedPage<Goods> result = template.queryForPage(queryBuilder.build(), Goods.class);
        //解析结果
        Aggregations aggs = result.getAggregations();
        for (SpecParam param : params) {
            String name = param.getName();
            StringTerms terms = aggs.get(name);
            List<String> options = terms.getBuckets().stream().map(b -> b.getKeyAsString()).collect(Collectors.toList());
            Map<String, Object> map = new HashMap<>();
            map.put("k", name);
            map.put("options", options);
            specs.add(map);
        }

        return specs;
    }

    private List<Brand> parseBrandAgg(LongTerms longTerms) {
        try {
            List<Long> ids = longTerms.getBuckets().stream().map(b -> b.getKeyAsNumber().longValue()).collect(Collectors.toList());
            List<Brand> brands = brandClient.queryBrandsByIds(ids);
            return brands;
        } catch (Exception e) {
            log.error("[搜索服务] 查询品牌异常", e);
            return null;
        }
    }

    private List<Category> parseCategoryAgg(LongTerms longTerms) {
        try {
            List<Long> ids = longTerms.getBuckets().stream().map(b -> b.getKeyAsNumber().longValue()).collect(Collectors.toList());
            List<Category> categories = categoryClient.queryCategoryByIds(ids);
            return categories;
        } catch (Exception e) {
            log.error("[搜索服务] 查询分类异常", e);
            return null;
        }
    }


    /**
     * 添加索引
     * @param spuId
     */
    public void createOrUpdateIndex(Long spuId) {
        //查询
        Spu spu = goodsClient.querySpuById(spuId);
        //构建goods
        Goods goods = buildGoods(spu);
        //存入索引库
        goodsRepository.save(goods);
    }

    /**
     * 删除索引
     * @param spuId
     */
    public void deleteIndex(Long spuId) {
        goodsRepository.deleteById(spuId);
    }
}
