package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Date:2021/11/26
 * Author:ybc
 * Description:
 */
public class MyBatisTest {

    /**
     * SqlSession默认不自动提交事务，若需要自动提交事务
     * 可以使用SqlSessionFactory.openSession(true);
     */


    @Test
    public void testMyBatis() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取SqlSession，且自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //测试功能
        User user = new User(null, "zs", "111111", 24, "男", "aaa@qq.com");
        int result = mapper.insertUser(user);
        //提交事务
        //sqlSession.commit();
        System.out.println("result:"+result);
    }

    @Test
    public void testCRUD() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        // sqlSession.clearCache();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        DeptMapper deptMapper1 = sqlSession1.getMapper(DeptMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        DeptMapper deptMapper2 = sqlSession2.getMapper(DeptMapper.class);
        //mapper.updateUser();
        //mapper.deleteUser();

        // User user = mapper.getUserById(3);
        // System.out.println(user.getAge());
        // Dept dept = deptMapper.getDeptById(2);
        // System.out.println(dept);

        // List<User> list = mapper.getAllUser("t_user");
        // List<Map> list = mapper.getAllUser("t_user", "id");
        List<User> list1 = mapper1.getAllUser("");
        // sqlSession1.commit();
        // sqlSession1.close();
        // List<User> list2 = mapper1.getAllUser("");
        list1.forEach(user -> System.out.println(user));
        // list2.forEach(user -> System.out.println(user));
}

}
