package com.hcw.sell.dao;


import com.hcw.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 14:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findTest() {
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        Assert.assertNotNull(productCategory);
        log.info("productCategory={}", productCategory);

    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("肉类");
        productCategory.setCategoryType(2);
        ProductCategory save = productCategoryRepository.save(productCategory);
        log.info("save={}", save);
    }
//根据类目查询
    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
        log.info("byCategoryTypeIn={}", byCategoryTypeIn);
    }

}