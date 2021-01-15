package com.hcw.sell.Utils;

import com.hcw.sell.vo.ResultVo;

/**
 * @author hgcw
 * @date 2021/1/7 19:28
 */
//封装类
public class ResultVoUtil {
    public static ResultVo success(Object object) {

        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMassage("成功");
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMassage(msg);
        return resultVo;
    }
}
