package com.bill.seckill.exception;

import com.bill.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName GlobalException
 * @Description
 * @Author bill
 * @Date 2021/12/19 21:31
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;
}
