package com.atguigu;

import com.atguigu.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// 指定测试类在Spring的环境中执行，可以通过注入的方式来获取bean对象
@RunWith(SpringJUnit4ClassRunner.class)
// 读取配置
@ContextConfiguration("classpath:spring-jdbc.xml")
public class TestJDBC {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate() {
        String sql = "update t_user set ssm.t_user.user_name = ? where id = ?";
        jdbcTemplate.update(sql, "ccc", 1);
    }

    @Test
    public void testQueryOne() {
        String sql = "select * from t_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 1);
        System.out.println(user);
    }

    @Test
    public void testQuery() {

        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }
}
