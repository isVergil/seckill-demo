package com.bill.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.seckill.pojo.User;
import com.bill.seckill.vo.LoginVo;
import com.bill.seckill.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bill
 * @since 2021-12-16
 */
public interface IUserService extends IService<User> {

    //登录
    RespBean doLogin(LoginVo loginVo);
}
