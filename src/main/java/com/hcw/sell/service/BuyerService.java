package com.hcw.sell.service;

import com.hcw.sell.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hgcw
 * @date 2021/1/9 22:26
 */
//查询，取消订单如果只传入orderId的话随便都可以查其他人的订单，所以要查询数据库订单的openid是和传入的一样
@Service
public interface BuyerService {
    //查询单个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
