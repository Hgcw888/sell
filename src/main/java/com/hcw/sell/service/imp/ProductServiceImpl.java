package com.hcw.sell.service.imp;

import com.hcw.sell.dao.ProductInfoRepository;
import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.dto.CartDTO;
import com.hcw.sell.enums.ProductStatusEnum;
import com.hcw.sell.enums.ResultEnum;
import com.hcw.sell.exception.SellException;
import com.hcw.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author hgcw
 * @date 2021/1/6 20:20
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    //根据ID查询商品
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    //查询所有在架商品
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    //查询所有商品
    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    //添加更新商品
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    //加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);


        }

    }

    //减库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);


        }

    }
}
