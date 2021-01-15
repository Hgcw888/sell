package com.hcw.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hcw.sell.dataobject.OrderDetail;
import com.hcw.sell.dto.OrderDTO;
import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import com.hcw.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/8 19:41
 */
//类型转换
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

      List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】 错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
