package com.hcw.sell.dao;

import com.hcw.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 14:25
 */
//类目标dao接口
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
//根据类目编号查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
