package com.bill.seckill.vo;

import com.bill.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @ClassName LoginVo
 * @Description TODO
 * @Author bill
 * @Date 2021/12/17 16:57
 * @Version 1.0
 **/
//登录参数

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
