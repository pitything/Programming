package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/shareData")
public class ShareDataController {
    @RequestMapping("/shareData01")
    public String shareData01(HttpServletRequest request) {
        request.setAttribute("shareData", "hello,request");
        return "success";
    }

    @RequestMapping("/shareData02")
    public ModelAndView shareData02() {
        /*** ModelAndView有Model和View的功能
         * * Model主要用于向请求域共享数据
         * * View主要用于设置视图，实现页面跳转 */
        ModelAndView mav = new ModelAndView();
        //向请求域共享数据
        mav.addObject("shareData", "hello,ModelAndView");
        //设置视图，实现页面跳转
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/shareData03")
    public String shareData03(Model model) {
        model.addAttribute("shareData", "hello,Model");
        return "success";
    }

    @RequestMapping("/shareData04")
    public String shareData04(Map<String, Object> map) {
        map.put("shareData", "hello,Map");
        return "success";
    }

    @RequestMapping("/shareData07")
    public String shareData07(ModelMap modelMap) {
        modelMap.addAttribute("shareData", "hello,ModelMap");
        return "success";
    }

    @RequestMapping("/shareData05")
    public String shareData05(HttpSession session) {
        session.setAttribute("shareData", "hello,session");
        return "success";
    }

    @RequestMapping("/shareData06")
    public String shareData06(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("shareData", "hello,application");
        return "success";
    }


}
