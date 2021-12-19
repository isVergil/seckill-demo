## 1、基本配置
* Mysql 过期
```
https://blog.csdn.net/lidachao01/article/details/72385498
```
* 选择 springboot 自带默认最快连接池 hikari 

* 数据库字符集
```
utf8mb4 -- UTF-8 Unicode (可以存 emojo 表情)
```
* 数据库表结构
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
* 自定义注解参数校验
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
* SpingBoot 全局异常处理

> 使用@ControllerAdvice 和@ExceptionHandler 注解。只能处理控制器抛出的异常。此时请求已经进入控制器中。

> 使用ErrorController类来实现。处理所有的异常，自由度大。

* Nginx 默认负载均衡模式 轮询
> 请求按照时间顺序分发到后端应用，多台服务器存在 session 不存在的问题，需要重新登录

> 解决：session 复制、前端存储、session 粘滞、后端集中处理 4种