package com.bill.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName MD5Util
 * @Description MD5 工具类
 * @Author bill
 * @Date 2021/12/16 20:31
 * @Version 1.0
 **/
public class MD5Util {

    private static final String salt = "bill";

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String inputPSW2FromPSW(String inputPSW) {
        //203123456ll
        //警惕两个char 类型的直接相加！
        String str = "" + salt.charAt(0) + salt.charAt(1) + inputPSW + salt.charAt(2) + salt.charAt(3);
        return md5(str);
    }

    public static String fromPSW2DBPSW(String fromPSW, String salt) {
        //salt 混淆 保证安全性
        String str = salt.charAt(0) + salt.charAt(1) + fromPSW + salt.charAt(2) + salt.charAt(3);
        return md5(str);
    }

    public static String inputPSW2DBPSW(String inputPSW, String salt) {
        String fromPSW = inputPSW2FromPSW(inputPSW);
        String DBPSW = fromPSW2DBPSW(fromPSW, salt);
        return DBPSW;
    }

    public static void main(String[] args) {
        System.out.println(inputPSW2FromPSW("123456"));   //87d1b89eb8c0283affc88b5e95023b68
        System.out.println(fromPSW2DBPSW("87d1b89eb8c0283affc88b5e95023b68", "bill"));
        System.out.println(inputPSW2DBPSW("123456", "bill"));
    }
}
