/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: OrderService.java
 * Author: lmy
 * Date: 19-12-26 下午7:08
 * Version: 1.0
 * LastModified: 19-12-26 下午7:08
 */

package com.leyou.order.service;
/**
 * @Project leyou
 * @Package com.leyou.order.service
 * @author lmy
 * @date 2019/12/26 19:08
 * @version V1.0
 */

import com.leyou.common.dto.CartDto;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.IdWorker;
import com.leyou.item.pojo.Sku;
import com.leyou.order.client.AddressClient;
import com.leyou.order.client.GoodsClient;
import com.leyou.order.dto.OrderDto;
import com.leyou.order.enums.OrderStatusEnum;
import com.leyou.order.enums.PayState;
import com.leyou.order.interceptor.UserInterceptor;
import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.mapper.OrderMapper;
import com.leyou.order.mapper.OrderStatusMapper;
import com.leyou.order.pojo.AddressDto;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.utils.PayHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lmy
 * @ClassName OrderService
 * @Description 订单功能业务类
 * @date 2019/12/26 19:08
 **/
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GoodsClient goodsClient;


    @Autowired
    private PayHelper payHelper;

    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderDto orderDto) {
        // 新增订单
        Order order = new Order();
        // 生成订单编号
        long orderId = idWorker.nextId();
        // 基本信息
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setPaymentType(orderDto.getPaymentType());

        // 用户信息
        UserInfo user = UserInterceptor.getUser();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        order.setBuyerRate(false);


        // 收货人信息
        AddressDto addr = AddressClient.findById(orderDto.getAddressId());

        order.setReceiver(addr.getName());
        order.setReceiverAddress(addr.getAddress());
        order.setReceiverCity(addr.getCity());
        order.setReceiverDistrict(addr.getDistrict());
        order.setReceiverMobile(addr.getPhone());
        order.setReceiverState(addr.getState());
        order.setReceiverZip(addr.getZipCode());

        // 金额
        Map<Long, Integer> numMap = orderDto.getCarts().stream().collect(Collectors.toMap(CartDto::getSkuId, CartDto::getNum));
        Set<Long> idsSet = numMap.keySet();
        List<Sku> skus = goodsClient.querySkuByIds(new ArrayList<>(idsSet));

        List<OrderDetail> details = new ArrayList<>();

        long totalPay = 0L;
        for (Sku sku : skus) {
            // 计算总价
            totalPay +=sku.getPrice() * numMap.get(sku.getId());
            // 封装orderDetail
            OrderDetail detail = new OrderDetail();
            detail.setImage(StringUtils.substringBefore(sku.getImages(), ","));
            detail.setNum(numMap.get(sku.getId()));
            detail.setOrderId(orderId);
            detail.setOwnSpec(sku.getOwnSpec());
            detail.setPrice(sku.getPrice());
            detail.setSkuId(sku.getId());
            detail.setTitle(sku.getTitle());

            details.add(detail);
        }
        // 总金额
        order.setTotalPay(totalPay);
        order.setPostFee(0L);
        // 实付金额
        order.setActualPay(totalPay - 0);
        int count = orderMapper.insertSelective(order);
        if(count != 1){
            log.error("[创建订单] 创建订单失败， orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_ERROR);
        }
        // 新增订单详情
        count = orderDetailMapper.insertList(details);
        if(count != details.size()){
            log.error("[创建订单] 创建订单失败， orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_ERROR);
        }
        // 新增订单状态
        OrderStatus status = new OrderStatus();
        status.setCreateTime(order.getCreateTime());
        status.setOrderId(orderId);
        status.setStatus(OrderStatusEnum.UN_PAY.value());

        count = orderStatusMapper.insertSelective(status);
        if(count != 1){
            log.error("[创建订单] 创建订单失败， orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_ERROR);
        }
        // 修改库存
        goodsClient.decreaseStock(orderDto.getCarts());

        return orderId;
    }

    public Order queryOrder(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order == null){
            log.error("[订单查询服务] 订单不存在");
            throw new LyException(ExceptionEnum.ORDER_NOT_FOUND);
        }
        // 查询订单详情
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(id);
        List<OrderDetail> details = orderDetailMapper.select(detail);
        if(CollectionUtils.isEmpty(details)){
            log.info("[订单查询服务] 订单详情没有查询到");
            throw new LyException(ExceptionEnum.ORDER_DETAIL_NOT_FOUND);
        }
        order.setOrderDetails(details);

        // 查询订单状态
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(id);
        if(orderStatus == null){
            log.info("[订单查询服务] 订单状态没有查询到");
            throw new LyException(ExceptionEnum.ORDER_STATUS_NOT_FOUND);
        }
        order.setOrderStatus(orderStatus);
        return order;
    }


    public String createPayUrl(Long orderId) {
        // 查询订单获取订单金额
        Order order = queryOrder(orderId);
        // 判断订单状态
        Integer status = order.getOrderStatus().getStatus();
        if (status != OrderStatusEnum.UN_PAY.getCode()) {
            throw new LyException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //Long actualPay = order.getActualPay();
        Long actualPay = 1L;
        OrderDetail detail = order.getOrderDetails().get(0);
        String desc = detail.getTitle();
        return payHelper.createPayUrl(orderId, actualPay, desc);
    }

    public void handleNotify(Map<String, String> result) {
        // 数据校验
        payHelper.isSuccess(result);
        // 校验签名
        payHelper.isValidSign(result);
        // 金额
        String totalFeeStr = result.get("total_fee");
        // 订单编号
        String tradeNoStr = result.get("out_trade_no");
        if (StringUtils.isBlank(tradeNoStr) || StringUtils.isBlank(totalFeeStr)) {
            throw new LyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }
        Long totalFee = Long.valueOf(totalFeeStr);
        Long orderId = Long.valueOf(tradeNoStr);
        // 订单号校验
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            throw new LyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }
        // FIXME 这里应该是不等于实际金额
        //  if (totalFee != order.getActualPay())
        // 金额校验
        if (totalFee != 1L) {
            // 金额不符
            throw new LyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }
        // 修改订单状态
        OrderStatus status = new OrderStatus();
        status.setStatus(OrderStatusEnum.PAYED.getCode());
        status.setOrderId(orderId);
        status.setPaymentTime(new Date());
        int count = orderStatusMapper.updateByPrimaryKeySelective(status);
        if (count != 1) {
            log.error("[订单微信回调服务] 更新订单状态失败");
            throw new LyException(ExceptionEnum.UPDATE_ORDER_STATUS_ERROR);
        }
        log.info("[订单回调, 订单支付成功!], 订单编号:{}", orderId);
    }

    public PayState queryOrderState(Long orderId) {
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        Integer status = orderStatus.getStatus();
        if (status != OrderStatusEnum.UN_PAY.getCode()) {
            // 如果已支付, 真的是已支付
            return PayState.SUCCESS;
        }
        // 如果未支付, 但其实不一定是未支付, 必须去微信查询支付状态
        return payHelper.queryPayState(orderId);
    }
}
