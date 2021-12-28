package com.bill.seckill.controller;

import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName GoodsController
 * @Description 商品
 * @Author bill
 * @Date 2021/12/19 22:00
 * @Version 1.0
 **/

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    /**
     * @author bill
     * @description 跳转到商品列表页
     * @updateTime 2021/12/19  22:09
     */
//    @RequestMapping("/toList")
//    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket) {
//        if (StringUtils.isEmpty(ticket)) {
//            return "login";
//        }
//        //User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        if (null == user) {
//            return "login";
//        }
//        model.addAttribute("user", user);
//        return "goodsList";
//    }
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        return "goodsList";
    }


    /**
     * @author bill
     * @description 跳转到商品详情页
     * @updateTime 2021/12/28  14:58
     */
    @RequestMapping("/toDetail")
    public String toDetail(Model model, User user) {
//        if (StringUtils.isEmpty(ticket)) {
//            return "login";
//        }
//        //User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        if (null == user) {
//            return "login";
//        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}
