package com.atguigu._1fruit.servlet;

import com.atguigu._1fruit.dao.FruitDAO;
import com.atguigu._1fruit.dao.impl.FruitDAOImpl;
import com.atguigu._1fruit.pojo.Fruit;
import com.atguigu.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));

        //4.资源跳转
        // 这行代码是请求转发的作用，跳转、不刷新数据、不改变地址栏，因此需要使用重定向，将地址栏的修改，并重新查询数据
        // super.processTemplate("index",request,response);
        // 跳转、 不刷新数据、不改变地址栏
        // request.getRequestDispatcher("index.html").forward(request,response);
        // 跳转、刷新数据、不改变地址栏
        request.getRequestDispatcher("index").forward(request,response);

        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        // 跳转、刷新数据、改变地址栏
        // response.sendRedirect("index");
    }
}

// java.lang.NumberFormatException: For input string: ""