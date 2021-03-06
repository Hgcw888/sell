package com.hcw.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author hgcw
 * @date 2021/1/8 19:12
 */
//表单验证
@Data
public class OrderForm {
    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;
    //买家手机
    @NotEmpty(message = "手机号必填")
    private String phone;
    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;
    //买家openid
    @NotEmpty(message = "openid")
    private String openid;
    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
