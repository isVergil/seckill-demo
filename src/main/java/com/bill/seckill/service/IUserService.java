package com.bill.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.seckill.pojo.User;
import com.bill.seckill.vo.LoginVo;
import com.bill.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bill
 * @since 2021-12-16
 */
public interface IUserService extends IService<User> {

    //登录
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据 cookie 获取用户
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}
