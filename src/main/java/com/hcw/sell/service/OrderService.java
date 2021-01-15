package com.hcw.sell.service;

import com.hcw.sell.dto.OrderDTO;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/7 20:49
 */
//订单service接口
public interface OrderService {
    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询个人订单列表
    List<OrderDTO> findList(String buyerOpenid);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);
}
