package com.hcw.sell.service;

import com.hcw.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 18:34
 */
//商品类目service接口
public interface CategoryService {
    //查询单个类目
    ProductCategory findOne(Integer categoryId);

    //查询所有类目
    List<ProductCategory> findAll();

    //按照类目编号查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    //添加更新类目
    ProductCategory save(ProductCategory productCategory);

}
