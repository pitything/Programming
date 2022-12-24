package com.itheima.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("testboot")
public class HellobootController {

    @RequestMapping("{id}")
    public String demo1(@PathVariable("id") int id){
        System.out.println("id : " + id);
        return "hello boot";
    }
}
