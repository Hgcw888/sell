package com.hcw.sell.dao;

import com.hcw.sell.dataobject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2021/1/7 20:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111");
        orderDetail.setOrderId("111");
        orderDetail.setProductId("1");
        orderDetail.setProductName("猪肉");
        orderDetail.setProductIcon("http://123.jpg");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(10);
        OrderDetail save = orderDetailRepository.save(orderDetail);
        log.info("save={}", save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("1610184302990390494");
        log.info("byOrderId={}", byOrderId);
    }
}