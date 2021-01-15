package com.hcw.sell.dao;

import com.hcw.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
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
 * @date 2021/1/7 20:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@DynamicUpdate
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("111");
        orderMaster.setBuyerName("boy");
        orderMaster.setBuyerPhone("15177992761");
        orderMaster.setBuyerAddress("深圳市宝安区");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(20));
        orderMasterRepository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        List<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid("110110");
        log.info("byBuyerOpenid={}", byBuyerOpenid);
    }
}