package com.bill.seckill.controller;

import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IGoodsService;
import com.bill.seckill.service.IUserService;
import com.bill.seckill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @Autowired
    private IGoodsService goodsService;

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
        //存放 list 数据
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    /**
     * @author bill
     * @description 跳转到商品详情页
     * @updateTime 2021/12/29  16:33
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //secKillStatus 和 remainSeconds 秒杀状态 计算
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        int secKillStatus = 0;  //秒杀状态
        int remainSeconds = 0;  //秒杀倒计时
        //secKillStatus
        //秒杀倒计时 0
        //秒杀进行中 1
        //秒杀已结束 2
        if (nowDate.before(startDate)) { //秒杀倒计时
            //secKillStatus = 0;
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
        } else if (nowDate.after(endDate)) {  //秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {  //秒杀进行中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("goods", goodsVo);
        model.addAttribute("user", user);
        return "goodsDetail";
    }
}
