package com.bill.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.seckill.pojo.Order;
import com.bill.seckill.pojo.User;
import com.bill.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bill
 * @since 2021-12-28
 */
public interface IOrderService extends IService<Order> {

    //生成秒杀订单
    Order seckill(User user, GoodsVo goods);
}
