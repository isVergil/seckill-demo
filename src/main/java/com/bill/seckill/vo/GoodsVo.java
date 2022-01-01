package com.bill.seckill.vo;

import com.bill.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GoodsVo
 * @Description 商品返回对象
 * @Author bill
 * @Date 2021/12/29 15:56
 * @Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

}
