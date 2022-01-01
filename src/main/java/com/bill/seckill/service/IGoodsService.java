package com.bill.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.seckill.pojo.Goods;
import com.bill.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bill
 * @since 2021-12-28
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * @author bill
     * @description  获取商品列表
     * @updateTime 2021/12/29  16:04
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
