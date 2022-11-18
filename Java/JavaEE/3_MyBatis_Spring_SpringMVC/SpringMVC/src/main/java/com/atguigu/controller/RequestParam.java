package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("requestParam")
@Controller
public class RequestParam {

    @RequestMapping(value = "testParam01", method = RequestMethod.GET)
    public String testParam01(HttpServletRequest request){
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

    @RequestMapping(value = "testParam02", method = RequestMethod.GET)
    public String testParam02(String username, String password){
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

    @RequestMapping(value = "testParam03", method = RequestMethod.GET)
    public String testParam03(@ org.springframework.web.bind.annotation.RequestParam("username") String username,
                              @ org.springframework.web.bind.annotation.RequestParam("password") String password){
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

    @RequestMapping(value = "testParam04", method = RequestMethod.GET)
    public String testParam04(String username, String password){
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

}
