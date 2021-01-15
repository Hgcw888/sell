package com.hcw.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author hgcw
 * @date 2021/1/6 10:56
 */
//类目表
@Data
@Entity
@DynamicUpdate
public class ProductCategory {
    //类目ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名称
    private String categoryName;
    //类目编号
    private Integer categoryType;
//    //
//    private Date createTime;
//    //
//    private Date updateTime;


    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
