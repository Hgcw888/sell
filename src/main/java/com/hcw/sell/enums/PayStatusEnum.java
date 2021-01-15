package com.hcw.sell.enums;

import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/1/7 20:01
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
