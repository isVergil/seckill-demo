package com.bill.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bill.seckill.pojo.Goods;
import com.bill.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bill
 * @since 2021-12-28
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * @author bill
     * @description 获取商品列表
     * @updateTime 2021/12/29  16:11
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
