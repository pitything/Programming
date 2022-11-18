package com.atguigu._5fruit_mvc_service_ioc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/* 通过注解的方式配置 ServletConfig
@WebServlet(urlPatterns = {"/demo01"} ,
        initParams = {
            @WebInitParam(name="hello",value="world"),
            @WebInitParam(name="uname",value="jim")
        }
        )
*/
public class ServletConfigContext extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 获取ServletConfig中的配置
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);

        // 获取ServletContext中的配置
        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = " + contextConfigLocation);
    }
}

//Servlet生命周期：实例化、初始化、服务、销毁
