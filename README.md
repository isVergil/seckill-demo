## 1、基本配置
* Mysql 过期
```
https://blog.csdn.net/lidachao01/article/details/72385498
```
<br> 

* 选择 springboot 自带默认最快连接池 hikari 

<br> 

* 数据库字符集
```
utf8mb4 -- UTF-8 Unicode (可以存 emojo 表情)
```
<br> 

* 自定义注解参数校验
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
<br> 

* SpingBoot 全局异常处理

> 使用@ControllerAdvice 和@ExceptionHandler 注解。只能处理控制器抛出的异常。此时请求已经进入控制器中。

> 使用ErrorController类来实现。处理所有的异常，自由度大。 
 
<br> 

* Nginx 默认负载均衡模式 轮询
> 请求按照时间顺序分发到后端应用，多台服务器存在 session 不存在的问题，需要重新登录

> 解决：session 复制、前端存储、session 粘滞、后端集中处理 4种  

<br> 

* 用户表结构
```
CREATE TABLE t_user(
	`id` BIGINT(20) NOT NULL COMMENT '用户id 手机号码',
	`nickname` VARCHAR(255) NOT NULL,
	`password` VARCHAR(32) DEFAULT  NULL COMMENT 'MD5(MD5(pass明文 + 固定salt) + salt) 用户端+服务端 双重MD5加密保证安全性',
	`salt` VARCHAR(10) DEFAULT NULL,
	`head` VARCHAR(128) DEFAULT NULL COMMENT '头像',
	`register_date` datetime DEFAULT NULL COMMENT '注册时间',
	`last_login_date` datetime DEFAULT NULL COMMENT '最后一次登录时间',
	`login_count`  int(11) DEFAULT '0'  COMMENT '登录次数',
	PRIMARY KEY(`id`)
);
```
<br> 

* 商品表结构
```
CREATE TABLE `t_goods` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
	`goods_name` VARCHAR(16) DEFAULT NULL COMMENT '商品名称',
	`goods_title` VARCHAR(64) DEFAULT NULL COMMENT '商品标题',
	`goods_img` VARCHAR(64) DEFAULT NULL COMMENT '商品图片',
	`goods_detail` LONGTEXT COMMENT '商品详情',
    `goods_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品价格',
    `goods_stock` INT(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
```
<br> 

* 订单表结构
```
CREATE TABLE `t_order` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
	`user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
	`goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
	`delivery_addr_id` BIGINT(20) DEFAULT NULL COMMENT '收货地址ID',
	`goods_name` VARCHAR(20) DEFAULT NULL COMMENT '商品名称',
	`goods_count` INT(11) DEFAULT '0' COMMENT '商品数量',
	`goods_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品单价',
	`order_channel` TINYINT(4) DEFAULT '0' COMMENT '下单渠道,1pc,2android,3ios',
	`status` TINYINT(4) DEFAULT '0' COMMENT '订单状态,0新建未支付,1已支付,2已发货,3已收货,4已退款,5已完成',
	`create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
	`pay_date` datetime DEFAULT NULL COMMENT '订单支付时间',
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
```
<br> 

* 秒杀商品表结构
```
CREATE TABLE `t_seckill_goods` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品ID',
	`goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
  `seckill_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '秒杀价格',
	`stock_count` INT(11) DEFAULT NULL COMMENT '库存数量',
	`start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
	`end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
```
<br> 

* 秒杀订单表结构
```
CREATE TABLE `t_seckill_order` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
	`user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` BIGINT(20) DEFAULT NULL COMMENT '订单ID',
  `goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
```
<br> 




