package com.hcw.sell.enums;

import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/1/6 20:28
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "商品在家"),
    DOWN(1, "商品已下架"),

    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
