package com.hcw.sell.controller;

import com.hcw.sell.Utils.ResultVoUtil;
import com.hcw.sell.converter.OrderForm2OrderDTOConverter;
import com.hcw.sell.dto.OrderDTO;
import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import com.hcw.sell.form.OrderForm;
import com.hcw.sell.service.BuyerService;
import com.hcw.sell.service.OrderService;
import com.hcw.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hgcw
 * @date 2021/1/8 19:09
 */
//订单接口{接口数据文档}
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private  BuyerService buyerService;

    //创建订单（创建订单方法创建成功返回一个订单号）
    @ResponseBody
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderFrom={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO creatResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderDTO={}", creatResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid) {
        if (openid == null) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDTO> list = orderService.findList(openid);
        return ResultVoUtil.success(list);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderOne = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderOne);
    }


    //取消订单
    @PostMapping("/cancel")
    public ResultVo<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

}
