package com.hcw.sell.service.imp;

import com.hcw.sell.dataobject.OrderDetail;
import com.hcw.sell.dto.OrderDTO;
import com.hcw.sell.enums.OrderStatusEnum;
import com.hcw.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hgcw
 * @date 2021/1/7 22:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    private final String BUYER_OPENID = "15177992761";
    private final String ORDER_ID = "1610195179333457119";
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("boy");
        orderDTO.setBuyerAddress("深圳市宝安区");
        orderDTO.setBuyerPhone("15177992461");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("2");
        orderDetail.setProductQuantity(10);
        orderDetailList.add(orderDetail);
        //将购物车添加到订单
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info("orderDTO1={}", orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        log.info("one={}", one);
        Assert.assertEquals(ORDER_ID, one.getOrderId());
    }

    @Test
    public void findList() {
        List<OrderDTO> list = orderService.findList(BUYER_OPENID);
        log.info("list={}", list);
    }

    @Test
    public void cancel() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.cancel(one);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.finish(one);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), cancel.getOrderStatus());

    }

    @Test
    public void paid() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.paid(one);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), cancel.getPayStatus());
    }
}