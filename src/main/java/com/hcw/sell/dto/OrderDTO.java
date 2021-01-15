package com.hcw.sell.dto;


import com.hcw.sell.dataobject.OrderDetail;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/7 20:52
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)//如果字段为空就不返回给前端
public class OrderDTO {
    private String orderId;
    //买家名字
    private String buyerName;
    //买家手机号
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信Openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态，默认为新下单
    private Integer orderStatus;
    //支付状态,默认为等待支付
    private Integer payStatus;
    //创建时间
    private Date createTime;
    //创建时间
    private Date updateTime;
    //订单包含多个商品
    List<OrderDetail> orderDetailList;
}
