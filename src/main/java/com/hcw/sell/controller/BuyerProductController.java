package com.hcw.sell.controller;

import com.hcw.sell.Utils.ResultVoUtil;
import com.hcw.sell.dataobject.ProductCategory;
import com.hcw.sell.dataobject.ProductInfo;
import com.hcw.sell.service.CategoryService;
import com.hcw.sell.service.ProductService;
import com.hcw.sell.vo.ProductInfoVo;
import com.hcw.sell.vo.ProductVo;
import com.hcw.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hgcw
 * @date 2021/1/6 21:25
 */
//商品接口
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list() {
        //1.查询说有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目(通过查询到的商品获取商品的类编号进行类目查询)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);

        }
        return ResultVoUtil.success(productVoList);

    }
}
