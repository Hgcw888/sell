package com.hcw.sell.dto;

import lombok.Data;

/**
 * @author hgcw
 * @date 2021/1/7 21:44
 */
//购物车
@Data
public class CartDTO {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
