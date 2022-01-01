package com.bill.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bill.seckill.pojo.Order;
import com.bill.seckill.pojo.SeckillOrder;
import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IGoodsService;
import com.bill.seckill.service.IOrderService;
import com.bill.seckill.service.ISeckillGoodsService;
import com.bill.seckill.service.ISeckillOrderService;
import com.bill.seckill.vo.GoodsVo;
import com.bill.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName SeckillController
 * @Description TODO
 * @Author bill
 * @Date 2021/12/30 22:27
 * @Version 1.0
 **/

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goods.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK);
            return "secKillfail";  //跳转失败页面
        }
        //判断是否重复抢购
        SeckillOrder order = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (order != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR);
            return "secKillfail";  //跳转失败页面
        }
        //下订单
        Order seckOrder = orderService.seckill(user, goods);
        model.addAttribute("order", seckOrder);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }
}
