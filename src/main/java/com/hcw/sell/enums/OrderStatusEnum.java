package com.hcw.sell.enums;

import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/1/7 19:56
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),

    FINISHED(1, "完结"),

    CANCEL(2, "已取消"),

    WAIT(3, "等待支付"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
