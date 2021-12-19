package com.bill.seckill.controller;

import com.bill.seckill.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * @author bill
     * @description 跳转到商品列表页
     * @updateTime 2021/12/19  22:09
     */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (null == user) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}
