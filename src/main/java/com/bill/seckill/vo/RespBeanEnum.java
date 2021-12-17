package com.bill.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    //通用
    SUCCESS(200, "SUCCESS"),

    ERROR(500, "服务端异常"),

    //登录模块
    LOGIN_ERROT(500210, "用户名或密码不正确"),

    MOBILE_ERROT(500211, "手机号码格式不正确");
    

    private final Integer code;
    private final String message;
}