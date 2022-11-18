package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/test", "/test2"}, method = {RequestMethod.GET, RequestMethod.POST})
public class RequestMappingController {
    //此时请求映射所映射的请求的请求路径为：/test/testRequestMapping
    @RequestMapping("/**/aaa")
    public String testRequestMapping() {
        return "index";
    }

    @RequestMapping(value = "/testRequestMapping2", params = {"username", "password!=123456"})
    public String testRequestMapping2() {
        return "index";
    }

    @RequestMapping(value = "/test03/{id}/{name}")
    public String testRequestMapping3(@PathVariable("id")int id, @PathVariable("name") String name){
        System.out.println("id: " + id + "， name: " + name);
        return "index";
    }
}