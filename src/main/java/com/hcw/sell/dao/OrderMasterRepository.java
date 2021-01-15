package com.hcw.sell.dao;

import com.hcw.sell.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/7 20:12
 */
//订单dao层接口
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    //根据买家Openid
    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);
}
