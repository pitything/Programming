package com.atguigu.controller.testajax;

import com.atguigu.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestAjaxController {

    //将json格式的数据转换为map集合
    // @RequestMapping("/test/RequestBody/json")
    // public String testRequestBody(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
    //     System.out.println(map);
    //     //{username=admin, password=123456}
    //     response.getWriter().print("hello,axios");
    //     return "success";
    // }

    //将json格式的数据转换为实体类对象
    @RequestMapping("/test/RequestBody/json")
    public void testRequestBody(@RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println(user);
        //User{id=null, username='admin', password='123456', age=null, gender='null'}
        response.getWriter().print("hello,axios");
    }

    // @RequestMapping("/testResponseBody")
    // public String testResponseBody() {
    //     //此时会跳转到逻辑视图success所对应的页面
    //     return "success";
    // }
    //
    // @RequestMapping("/testResponseBody")
    // @ResponseBody
    // public String testResponseBody() {
    //     //此时响应浏览器数据success
    //     return "success";
    // }

    @ResponseBody
    @RequestMapping("/test/ResponseBody/getUser")
    public User getUser() {
        return new User("user1", "pwd1");
    }

    @ResponseBody
    @RequestMapping("/test/ResponseBody/getUserMap")
    public Map<Integer, User> getUserMap() {
        Map<Integer, User> map = new HashMap<>();
        map.put(1, new User("user1", "pwd1"));
        map.put(2, new User("user2", "pwd2"));
        map.put(3, new User("user3", "pwd3"));
        return map;
    }

    @ResponseBody
    @RequestMapping("/test/ResponseBody/getUserList")
    public List<User> getUserList() {
        List<User> users = new ArrayList<User>() {{
            add(new User("user1", "pwd1"));
            add(new User("user2", "pwd2"));
            add(new User("user3", "pwd3"));
        }};
        return users;
    }
}
