package com.shopping.util;

import java.util.Random;

public class StringUtil {

    public static String generateStr() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        Random random = new Random();
        String str = String.valueOf(millis +random.nextInt(9));
        return str;
    }

    public static void main(String[] args) {
        System.out.println(generateStr());
    }
}
