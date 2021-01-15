package com.hcw.sell.service;

import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.dto.CartDTO;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 20:15
 */
public interface ProductService {
    //根据ID查询商品
    ProductInfo findOne(String productId);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    //查询所有商品
    List<ProductInfo> findAll();

    //添加更新产品
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
