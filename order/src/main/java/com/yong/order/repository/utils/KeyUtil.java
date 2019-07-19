package com.yong.order.repository.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成随机数
     * 格式为：时间戳+随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer i = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + i + "";
    }
}
