package com.hcw.sell.Utils;

import java.util.Random;

/**
 * @author hgcw
 * @date 2021/1/7 21:25
 */
//生成唯一的主键
// 格式：时间+随机数
public class  KeyUtil {
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
