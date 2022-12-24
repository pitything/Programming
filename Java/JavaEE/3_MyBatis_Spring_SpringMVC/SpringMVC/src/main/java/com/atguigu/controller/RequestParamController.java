package com.atguigu.controller;

import com.atguigu.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("requestParam")
@Controller
public class RequestParamController {

    @RequestMapping(value = "testParam01", method = RequestMethod.GET)
    public String testParam01(HttpServletRequest request) {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

    @RequestMapping(value = "testParam02", method = RequestMethod.GET)
    public String testParam02(String username, String password) {
        System.out.println("username: " + username + ", password: " + password);
        return "index";
    }

    @RequestMapping(value = "testParam03", method = RequestMethod.GET)
    public String testParam03(@org.springframework.web.bind.annotation.RequestParam(value = "username", defaultValue = "aaa", required = false) String username,
                              @org.springframework.web.bind.annotation.RequestParam("password") String password,
                              @RequestHeader("Referer") String referer,
                              @CookieValue("JSESSIONID") String jsessionid) {
        System.out.println("username: " + username + ", password: " + password);
        System.out.println("referer: " + referer);
        System.out.println("JSESSIONID: " + jsessionid);
        return "index";
    }

    @RequestMapping(value = "testParam04", method = RequestMethod.GET)
    public String testParam04(User user) {
        System.out.println("username: " + user.username + ", password: " + user.password);
        return "index";
    }

}
