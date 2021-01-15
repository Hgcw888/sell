package com.hcw.sell.service.imp;

import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hgcw
 * @date 2021/1/6 20:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    //查询商品
    @Test
    public void findOne() {
        ProductInfo one = productService.findOne("1");
        log.info("one={}", one);
    }

    //查询所有在架商品
    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0, upAll.size());
        log.info("upAll={}", upAll);
    }

    //查询所有商品
    @Test
    public void findAll() {
        List<ProductInfo> all = productService.findAll();
        Assert.assertNotEquals(0, all.size());
        log.info("all={}", all);
    }

    //添加跟新商品
    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("鸡肉");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很美味");
        productInfo.setProductIcon("http://345.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo save = productService.save(productInfo);
        log.info("save={}", save);
    }
}