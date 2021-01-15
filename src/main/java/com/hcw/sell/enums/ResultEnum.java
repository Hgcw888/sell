package com.hcw.sell.enums;

import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/1/7 21:07
 */
//定义异常类
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单bu存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在存在"),
    ORDRE_STATUS_ERROR(14, "订单状态不存在"),
    ORDER_UPDATE_FAIN(15, "订单更新失败"),
    ORDER_UPDATE_EMPTY(16, "订单详情为空"),
    ORDRE_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车不能为空"),
    DRDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(20, "微信公众账号方面错误");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
