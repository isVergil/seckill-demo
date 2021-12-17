package com.bill.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName Test
 * @Description TODO
 * @Author bill
 * @Date 2021/12/16 19:51
 * @Version 1.0
 **/
@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "bill");
        return "hello";
    }
}
