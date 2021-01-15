package com.hcw.sell.service.imp;

import com.hcw.sell.dto.OrderDTO;
import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import com.hcw.sell.service.BuyerService;
import com.hcw.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hgcw
 * @date 2021/1/9 22:31
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】 查不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }


    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断订单是否一致
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(orderId)) {
            log.error("【查询订单】 订单的openid不一致，openid={},orderDTO={}", openid, orderId);
            throw new SellException(ResultEnum.DRDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
