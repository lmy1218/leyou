/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: GoodsServiceImpl.java
 * Author: lmy
 * Date: 19-12-9 上午11:42
 * Version: 1.0
 * LastModified: 19-12-9 上午11:42
 */

package com.leyou.item.service.impl;
/**
 * @Project leyou
 * @Package com.leyou.item.service.impl
 * @author lmy
 * @date 2019/12/9 11:42
 * @version V1.0
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.dto.CartDto;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lmy
 * @ClassName GoodsServiceImpl
 * @Description TODO
 * @date 2019/12/9 11:42
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryService categoryServiceImpl;

    @Autowired
    private BrandService brandServiceImpl;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索字段过滤
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title", "%"+key+"%");
        }
        //上下架过滤
        if (saleable != null){
            criteria.andEqualTo("saleable", saleable);
        }
        //默认排序
        example.setOrderByClause("last_update_time DESC");
        //查询
        List<Spu> list = spuMapper.selectByExample(example);
        //判断
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        //解析分类和品牌名称
        loadCategoryAndBrandName(list);
        PageInfo<Spu> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(), info.getList());
    }


    /**
     * 新增上商品
     * @param spu
     */
    @Override
    @Transactional(rollbackFor = LyException.class)
    public void saveGoods(Spu spu) {
        //新增spu
        spu.setId(null);
        Date date = new Date();
        spu.setCreateTime(date);
        spu.setLastUpdateTime(date);
        spu.setSaleable(true);
        spu.setValid(false);

        int index = spuMapper.insertSelective(spu);
        if(index != 1){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //新增detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        spuDetailMapper.insertSelective(spuDetail);
        //新增Sku和stock
        saveSkuAndStock(spu);
        //发送mq消息
        amqpTemplate.convertAndSend("item.insert", spu.getId());
    }


    @Override
    public SpuDetail queryDetailById(Long spuId) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        if(spuDetail == null){
            throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }
        return spuDetail;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }

        //查询库存
//        for (Sku s : skuList) {
//            Stock stock = stockMapper.selectByPrimaryKey(s.getId());
//            if(stock == null){
//                throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
//            }
//            s.setStock(stock.getStock());
//        }
        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        loadStockInSku(ids, skuList);
        return skuList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoods(Spu spu) {
        if (spu.getId() == null){
            throw new LyException(ExceptionEnum.GOODS_ID_CAUNOT_BE_NULL);
        }
        //删除sku
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skuList = skuMapper.select(sku);
        if (! CollectionUtils.isEmpty(skuList)){
            skuMapper.delete(sku);
            //删除stock
            List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            stockMapper.deleteByIdList(ids);
        }

        //修改spu
        spu.setValid(null);
        spu.setSaleable(null);
        spu.setLastUpdateTime(new Date());
        spu.setCreateTime(new Date());

        int index = spuMapper.updateByPrimaryKeySelective(spu);
        if (index != 1){
            throw new LyException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }
        //修改detail
        index = spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (index != 1){
            throw new LyException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }
        //新增sku和stock
        saveSkuAndStock(spu);
        //发送mq消息
        amqpTemplate.convertAndSend("item.update", spu.getId());
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu querySpuById(Long id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if(spu == null){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        //查询sku
        spu.setSkus(querySkuBySpuId(id));
        //查询detail
        spu.setSpuDetail(queryDetailById(id));
        return spu;
    }

    @Override
    public List<Sku> querySkuByIds(List<Long> ids) {
        List<Sku> skus = skuMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(skus)){
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        loadStockInSku(ids, skus);

        return skus;
    }


    @Override
    @Transactional
    public void decreaseStock(List<CartDto> carts) {
        for (CartDto cart : carts) {
            // 减库存
            int count = stockMapper.decreasestock(cart.getSkuId(), cart.getNum());
            if(count != 1){
                throw new LyException(ExceptionEnum.STOCK_NOT_ENOUGH);
            }
        }
    }

    private void loadStockInSku(List<Long> ids, List<Sku> skus) {
        //查询库存
        List<Stock> stockList = stockMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(stockList)){
            throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
        }
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skus.forEach(s -> s.setStock(stockMap.get(s.getId())));
    }

    private void loadCategoryAndBrandName(List<Spu> list) {
        for (Spu spu : list) {
            //处理分类名称
            List<String> names = categoryServiceImpl.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names, "/"));
            //处理品牌名称
            spu.setBname(brandServiceImpl.queryById(spu.getBrandId()).getName());
        }
    }

    private void saveSkuAndStock(Spu spu) {
        Date date = new Date();
        int index;
        List<Stock> stocks = new ArrayList<>();
        //新增sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setCreateTime(date);
            sku.setLastUpdateTime(date);
            sku.setSpuId(spu.getId());
            index = skuMapper.insertSelective(sku);
            if(index != 1){
                throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());

            stocks.add(stock);
        }
        //批量新增库存
        index = stockMapper.insertList(stocks);
        if(index != stocks.size()){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }
}
