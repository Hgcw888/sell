package com.hcw.sell.converter;

import com.hcw.sell.dataobject.OrderDetail;
import com.hcw.sell.dataobject.OrderMaster;
import com.hcw.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hgcw
 * @date 2021/1/8 14:38
 */
//类型转换
public class OrderMaster2OrderDTOConverter {


    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;

    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
