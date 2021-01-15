package com.hcw.sell.dataobject;

import com.hcw.sell.enums.OrderStatusEnum;
import com.hcw.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hgcw
 * @date 2021/1/6 20:14
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    //订单id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态,默认为等待支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
//    //创建时间
//    private Date createTime;
//    //创建时间
//    private Date updateTime;

}
