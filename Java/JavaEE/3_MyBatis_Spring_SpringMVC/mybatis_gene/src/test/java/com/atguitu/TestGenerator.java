package com.atguitu;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.pojo.UserExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestGenerator {

    @Test
    public void testSearch() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = sqlSessionFactoryBuilder.build(resourceAsStream).openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(10, 19);
        List<User> users = mapper.selectByExample(userExample);
        System.out.println(users);
    }

    @Test
    public void testPage() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = sqlSessionFactoryBuilder.build(resourceAsStream).openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Page<User> page = PageHelper.startPage(1, 5);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(10, 19);
        page = (Page<User>) mapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<User>(page);
        System.out.println(page);
        System.out.println(pageInfo);
    }
}
