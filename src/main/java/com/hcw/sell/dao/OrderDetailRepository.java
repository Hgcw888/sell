package com.hcw.sell.dao;

import com.hcw.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/7 20:16
 */

//订单详情的dao层接口
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    //根据订单Id查询订单详情
    List<OrderDetail> findByOrderId(String orderId);
}
