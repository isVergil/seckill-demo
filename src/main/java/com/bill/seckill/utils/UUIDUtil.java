package com.bill.seckill.utils;


import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description 生成 uuid （cookie 使用）
 * @Author bill
 * @Date 2021/12/19 21:47
 * @Version 1.0
 **/
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
