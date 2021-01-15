package com.hcw.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcw.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/1/6 21:35
 */
//商品包含类目层
@Data
public class ProductVo {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
