package com.hcw.sell.dao;

import com.hcw.sell.dataobject.ProductInfo;
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
 * @date 2021/1/6 19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    //查询上架商品
    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        log.info("byProductStatus={}", byProductStatus);
    }

    //根据商品id查询商品
    @Test
    public void findIdTest() {
        ProductInfo productInfo = productInfoRepository.findById("1").get();
        log.info("productInfo={}", productInfo);
    }
    //查询所有商品
    @Test
    public void findAll(){
        List<ProductInfo> all = productInfoRepository.findAll();
        log.info("all={}", all);
    }

    //添加商品
    @Test
    public void Save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("3");
        productInfo.setProductName("猪肉");
        productInfo.setProductPrice(new BigDecimal(30));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductIcon("http://234.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo save = productInfoRepository.save(productInfo);
        log.info("save={}", save);
    }
}