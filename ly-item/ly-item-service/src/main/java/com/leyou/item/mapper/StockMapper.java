/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: StockMapper.java
 * Author: lmy
 * Date: 19-12-9 下午10:19
 * Version: 1.0
 * LastModified: 19-12-9 下午10:19
 */

package com.leyou.item.mapper;
/**
 * @Project leyou
 * @Package com.leyou.item.mapper
 * @author lmy
 * @date 2019/12/9 22:19
 * @version V1.0
 */

import com.leyou.common.mapper.BaseMapper;
import com.leyou.item.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author lmy
 * @ClassName StockMapper
 * @Description 商品数量Mapper
 * @date 2019/12/9 22:19
 **/

public interface StockMapper extends BaseMapper<Stock> {

    @Update("UPDATE tb_stock SET stock = stock - #{num} WHERE sku_id = #{id} AND stock >= #{num}")
    int decreasestock(@Param("id") Long id, @Param("num") Integer num);

}
