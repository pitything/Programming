package com.atguigu.controller;

import org.springframework.stereotype.Controller;


@Controller
public class HelloWorld {
    // @RequestMapping注解：处理请求和控制器方法之间的映射关系
    // @RequestMapping注解的value属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
    // @RequestMapping("/")
    // public String index() {
    //     //设置视图名称
    //     return "index";
    // }
}