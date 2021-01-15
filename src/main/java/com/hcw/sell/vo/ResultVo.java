package com.hcw.sell.vo;

import lombok.Data;

/**
 * @author hgcw
 * @date 2021/1/6 21:30
 */
//http请求返回的最外层对象
@Data
public class ResultVo<T> {
    //错误码
    private Integer code;
    //提示信息
    private String massage;
    //具体内容
    private T Data;
}
