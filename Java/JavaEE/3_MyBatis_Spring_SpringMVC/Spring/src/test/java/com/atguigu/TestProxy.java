package com.atguigu;

import com.atguigu.Proxy.Calculator;
import com.atguigu.Proxy.CalculatorImpl;
import com.atguigu.Proxy.ProxyFactory;
import org.junit.Test;

public class TestProxy {
    @Test
    public void testStaticProxy(){
        int a = 5, b = 2;
        // System.out.println(new CalculatorStaticProxy(new CalculatorImpl()).add(a, b));

        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator)proxyFactory.getProxy();
        System.out.println(proxy.add(a, b));
    }
}
