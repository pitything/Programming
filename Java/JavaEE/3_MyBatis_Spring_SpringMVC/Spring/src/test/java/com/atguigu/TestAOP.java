package com.atguigu;

import com.atguigu.aop.Calculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
    @Test
    public void testAop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-xml.xml");
        // ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-annotation.xml");
        Calculator bean = applicationContext.getBean(Calculator.class);
        bean.divide(10, 4);
    }
}
