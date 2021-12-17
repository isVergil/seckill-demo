package com.bill.seckill.controller;

import com.bill.seckill.service.IUserService;
import com.bill.seckill.vo.LoginVo;
import com.bill.seckill.vo.RespBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author bill
 * @Date 2021/12/17 16:16
 * @Version 1.0
 **/

//不能用 @RestController =  @ResponseBody ＋ @Controller 合在一起的作用。
@Controller
@RequestMapping("/login")
//输出日志
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo) {
        return userService.doLogin(loginVo);
    }
}
