package com.hcw.sell.service.imp;

import com.hcw.sell.dao.ProductCategoryRepository;
import com.hcw.sell.dataobject.ProductCategory;
import com.hcw.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 18:38
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
//查询单个类目
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findById(1).get();

    }
//查询所有类目
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }
//根据类目编号查询
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);

    }
//添加更新类目
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
