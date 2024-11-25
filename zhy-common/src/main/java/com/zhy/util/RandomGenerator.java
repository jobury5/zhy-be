package com.zhy.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.RandomUtils;

public class RandomGenerator {

    //private static final int CODE_LENGTH = 4;

    public static String genRandomVerCode(int length) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomNumber = RandomUtils.nextInt(0, 10); // 生成0-9之间的随机数
            code.append(randomNumber);
        }

        return code.toString();
    }

    public static String genRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomUtil.randomString(chars, length);
    }

    public static String genAccessToken(){
//        String uuid = IdUtil.fastSimpleUUID();
//        long timestamp = DateUtil.current();
//        // 将UUID和时间戳组合成访问令牌
//        return uuid + "-" + timestamp;
        return IdUtil.fastSimpleUUID();
    }

    public static void main(String[] args) {
        String randomCode = genRandomVerCode(4);
        System.out.println("随机验证码是: " + randomCode);
    }

}
