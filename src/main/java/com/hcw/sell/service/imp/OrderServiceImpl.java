package com.hcw.sell.service.imp;

import com.hcw.sell.Utils.KeyUtil;
import com.hcw.sell.converter.OrderMaster2OrderDTOConverter;
import com.hcw.sell.dao.OrderDetailRepository;
import com.hcw.sell.dao.OrderMasterRepository;
import com.hcw.sell.dataobject.OrderDetail;
import com.hcw.sell.dataobject.OrderMaster;
import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.dto.CartDTO;
import com.hcw.sell.dto.OrderDTO;
import com.hcw.sell.enums.OrderStatusEnum;
import com.hcw.sell.enums.PayStatusEnum;
import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import com.hcw.sell.service.OrderService;
import com.hcw.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hgcw
 * @date 2021/1/7 20:59
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;

    //创建订单
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //订单id
        String orderId = KeyUtil.genUniqueKey();
        //先定于一个总价对象
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2.计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

        }
        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);//客户端创建成功data需返回一个订单号。
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;//客户端创建成功data需返回一个订单号。返回订单号
    }

    //查询订单
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        List<OrderDetail> byOrderIdList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(byOrderIdList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(byOrderIdList);
        return orderDTO;
    }

    //查询订单列表
    @Override
    public List<OrderDTO> findList(String buyerOpenid) {
        List<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid);
        //类型转换
        List<OrderDTO> convert = OrderMaster2OrderDTOConverter.convert(orderMasters);
        return convert;
    }

    //取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("[取消订单] 订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDRE_STATUS_ERROR);
        }
        //修改订单
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if (save == null) {
            log.error("[取消订单] 订单更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIN);

        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.info("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    //订单完结
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("[订单完结] 订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDRE_STATUS_ERROR);
        }
        //修改订单
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if (save == null) {
            log.error("[订单完结] 订单完结失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIN);

        }
        return orderDTO;
    }

    //支付订单
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单是否新订单
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("[订单支付] 订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus()
            );
            throw new SellException(ResultEnum.ORDRE_STATUS_ERROR);
        }
        //判断订单状态是否取消或者支付
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.info("[订单支付] 订单支付状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus()
            );
            throw new SellException(ResultEnum.ORDRE_PAY_STATUS_ERROR);
        }
        //修改订单状态为已支付2
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if (save == null) {
            log.error("[订单支付] 订单支付失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIN);
        }
        return orderDTO;
    }
}
