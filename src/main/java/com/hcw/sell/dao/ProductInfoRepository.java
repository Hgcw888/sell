package com.hcw.sell.dao;
import com.hcw.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 17:11
 */
//商品表dao接口
public interface ProductInfoRepository  extends JpaRepository<ProductInfo,String> {
    //查询所有在架商品
List<ProductInfo> findByProductStatus(Integer productStatus);

}

