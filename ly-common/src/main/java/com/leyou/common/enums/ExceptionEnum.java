package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *枚举异常信息
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    CATEGORY_NOT_FOND(404, "商品分类没查询到结果"),

    BRAND_NOT_FOUND(404, "商品品牌信息没查询到结果"),

    SPEC_GROUP_NOT_FOND(404, "商品规格组没查询到结果"),

    SPEC_PARAM_NOT_FOND(404, "商品规格信息没查询到结果"),

    GOODS_DETAIL_NOT_FOUND(404, "商品详情信息没查询到结果"),

    GOODS_NOT_FOUND(404, "商品信息没查询到结果"),

    GOODS_SKU_NOT_FOUND(404, "商品Sku没查询到结果"),

    GOODS_STOCK_NOT_FOUND(404, "商品库存没查询到结果"),

    GOODS_UPDATE_ERROR(500, "更新商品信息失败"),

    BRAND_SAVE_ERROR(500, "新增品牌失败"),

    UPLOAD_FILE_ERROR(500, "文件上传失败"),

    INVALID_FILE_TYPE(400, "无效的文件类型"),

    GOODS_SAVE_ERROR(500, "新增商品失败"),

    GOODS_ID_CAUNOT_BE_NULL(400, "商品id不能为空"),

    INVALID_USER_DATA_TYPE(400, "用户的数据类型无效"),

    INVALID_VERIFY_CODE(400, "验证码无效"),

    INVALID_USERNAME_PASSWORD(400, "用户名或密码无效"),

    CREATE_TOKEN_ERROR(500, "用户凭证生成失败"),

    UN_AUTHORIZED(403, "未授权"),

    CART_NOT_FOUND(404, "购物车为空"),

    CREATE_ORDER_ERROR(500, "创建订单失败"),

    STOCK_NOT_ENOUGH(500, "库存不足"),

    ORDER_NOT_FOUND(404, "订单不存在"),

    ORDER_DETAIL_NOT_FOUND(404, "订单详情不存在"),

    ORDER_STATUS_NOT_FOUND(404, "订单状态不存在"),

    WX_PAY_ORDER_FAIL(500, "微信下单失败"),

    INVALID_SIGN_ERROR(400, "微信签名无效"),

    INVALID_ORDER_PARAM(400, "此订单金额无效"),

    UPDATE_ORDER_STATUS_ERROR(500, "更新订单状态失败"),

    ORDER_STATUS_ERROR(400, "订单状态创建失败")

    ;
    private int code;
    private String msg;


}
