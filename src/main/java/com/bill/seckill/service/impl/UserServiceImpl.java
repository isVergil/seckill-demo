package com.bill.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.seckill.exception.GlobalException;
import com.bill.seckill.mapper.UserMapper;
import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IUserService;
import com.bill.seckill.utils.CookieUtil;
import com.bill.seckill.utils.MD5Util;
import com.bill.seckill.utils.UUIDUtil;
import com.bill.seckill.vo.LoginVo;
import com.bill.seckill.vo.RespBean;
import com.bill.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bill
 * @since 2021-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;   //飘红不用管不影响

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //使用 自定义注解参数校验 ismobile 替代
//        //即使前端做了校验，后端也要做校验  空值校验
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
//        }
//        //手机号码格式校验
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROT);
//        }

        User user = userMapper.selectById(mobile);
        //用自定义全局异常替代
//        //是否有该用户
//        if (null == user) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
//        }
        if (null == user) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROT);
        }
//        //密码是否正确
//        if (!MD5Util.fromPSW2DBPSW(password, user.getSalt()).equals(user.getPassword())) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
//        }
        if (!MD5Util.fromPSW2DBPSW(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROT);
        }

        //生成 Cookie
        String ticket = UUIDUtil.uuid();

        //request.getSession().setAttribute(ticket, user);   用户信息放到 session
        //用户信息放到 redis
        redisTemplate.opsForValue().set("user:" + ticket, user);

        CookieUtil.setCookie(request, response, "userTicket", ticket);

        return RespBean.success();
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }
}
