package com.bill.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.seckill.mapper.OrderMapper;
import com.bill.seckill.mapper.SeckillOrderMapper;
import com.bill.seckill.pojo.Order;
import com.bill.seckill.pojo.SeckillGoods;
import com.bill.seckill.pojo.SeckillOrder;
import com.bill.seckill.pojo.User;
import com.bill.seckill.service.IOrderService;
import com.bill.seckill.service.ISeckillGoodsService;
import com.bill.seckill.service.ISeckillOrderService;
import com.bill.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bill
 * @since 2021-12-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    /**
     * @author bill
     * @description 秒杀业务 减库存+生成订单+生产秒杀订单
     * @updateTime 2021/12/30  22:57
     */
    @Override
    public Order seckill(User user, GoodsVo goods) {
        //减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        //生产订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生产秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        //save 方法 id 为空 insert id不为空 update
        //https://blog.csdn.net/Horse7/article/details/103868144
        seckillOrderService.save(seckillOrder);
        return order;
    }
}
