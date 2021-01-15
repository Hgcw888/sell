package com.hcw.sell.exception;

import com.hcw.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/1/7 21:06
 */
//异常
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
//controller层表单需要
    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
