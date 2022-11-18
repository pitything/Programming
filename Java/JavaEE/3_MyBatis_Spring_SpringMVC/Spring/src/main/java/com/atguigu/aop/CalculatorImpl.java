package com.atguigu.aop;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        int res = a + b;
        System.out.println("add 结果：" + res);
        return res;
    }

    @Override
    public int substract(int a, int b) {
        int res = a - b;
        System.out.println("substract 结果：" + res);
        return res;
    }

    @Override
    public int multiply(int a, int b) {
        int res = a * b;
        System.out.println("multiply 结果：" + res);
        return res;

    }

    @Override
    public int divide(int a, int b) {
        int res = a / b;
        System.out.println("divide 结果：" + res);
        return res;
    }
}
