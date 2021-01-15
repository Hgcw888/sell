package com.hcw.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author hgcw
 * @date 2021/1/6 15:43
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    //商品ID
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品小图
    private String productIcon;
    //商品状态
    private Integer productStatus;
    //商品编号
    private Integer categoryType;
//    //创建时间
//    private Date creatTime;
//    //更新时间
//    private Date updateTime;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", productStatus=" + productStatus +
                ", categoryType=" + categoryType +
                '}';
    }
}
