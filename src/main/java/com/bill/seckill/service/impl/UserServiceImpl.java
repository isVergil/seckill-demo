package com.bill.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.seckill.mapper.UserMapper;
import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IUserService;
import com.bill.seckill.utils.MD5Util;
import com.bill.seckill.utils.ValidatorUtil;
import com.bill.seckill.vo.LoginVo;
import com.bill.seckill.vo.RespBean;
import com.bill.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public RespBean doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //即使前端做了校验，后端也要做校验  空值校验
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
        }
        //手机号码格式校验
        if (!ValidatorUtil.isMobile(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROT);
        }
        User user = userMapper.selectById(mobile);
        //是否有该用户
        if (null == user) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
        }
        //密码是否正确
        if (!MD5Util.fromPSW2DBPSW(password, user.getSalt()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROT);
        }
        return RespBean.success();
    }
}
