package com.hcw.sell.service.imp;

import com.hcw.sell.dataobject.ProductCategory;
import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hgcw
 * @date 2021/1/6 18:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
//查询单个类目
    @Test
    public void findOne() {
        ProductCategory one = categoryService.findOne(1);
        log.info("one={}", one);
    }
//查询所有类目
    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0, all.size());
        log.info("all={}", all);
    }
//根据类目编号查询
    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(list);
       log.info("byCategoryTypeIn={}",byCategoryTypeIn);
    }
//更新和添加类目
    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("护肤品");
        productCategory.setCategoryId(3);
        ProductCategory save = categoryService.save(productCategory);
        Assert.assertNull(save);
    }
}