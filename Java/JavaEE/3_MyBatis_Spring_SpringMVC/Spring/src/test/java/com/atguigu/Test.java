package com.atguigu;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.atguigu.beans.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.sql.SQLException;

public class Test {

    @org.junit.Test
    public void testHelloWorld() throws SQLException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // HelloWorld helloWorld = (HelloWorld)applicationContext.getBean("helloWorld");
        // HelloWorld helloWorld = (HelloWorld)applicationContext.getBean(HelloWorld.class);
        // HelloWorld helloWorld = (HelloWorld)applicationContext.getBean("helloWorld", HelloWorld.class);
        // helloWorld.sayHello();

        // TestClass testClass = (TestClass)applicationContext.getBean(TestInteface.class);
        // testClass.say();

        Student student = (Student) applicationContext.getBean("student");
        // Student student = (Student) applicationContext.getBean("student7");
        System.out.println(student);

        // singleton
        // Thread thread1 = new Thread(() -> {
        //     Student student = (Student) applicationContext.getBean("student7");
        //     System.out.println(student);
        // });
        // Thread thread2 = new Thread(() -> {
        //     Student student = (Student) applicationContext.getBean("student7");
        //     student.setName("aaa");
        //     System.out.println(student);
        // });
        // thread1.start();
        // thread2.start();

        // DruidDataSource druidDataSource = (DruidDataSource) applicationContext.getBean("druidDataSource");
        // DruidPooledConnection connection = druidDataSource.getConnection();
        // System.out.println(connection);

        // User user = applicationContext.getBean(User.class);
        // System.out.println(user);

    }

    @org.junit.Test
    public void testLife() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) ac.getBean("student");
        System.out.println(student);

        // User bean = ac.getBean(User.class);
        // System.out.println("生命周期：4、通过IOC容器获取bean并使用");
        // ac.close();
    }

    @org.junit.Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-factory.xml");
        User user = ac.getBean(User.class);
        System.out.println("生命周期：4、通过IOC容器获取bean并使用");
        System.out.println(user);
    }
}
