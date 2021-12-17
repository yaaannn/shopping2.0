package com.shopping.util;

import java.util.Random;

public class StringUtil {
    // 根据时间戳生成字符串
    public static String generateStr() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        String str = String.valueOf(millis +random.nextInt(9));
        return str;
    }
}
