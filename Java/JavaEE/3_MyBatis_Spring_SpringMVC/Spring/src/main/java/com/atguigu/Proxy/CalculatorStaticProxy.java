package com.atguigu.Proxy;

public class CalculatorStaticProxy implements Calculator {
    // 将被代理的目标对象声明为成员变量
    private Calculator target;

    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int a, int b) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + a + "," + b);
        // 通过目标对象来实现核心业务逻辑
        int addResult = target.add(a, b);
        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }

    @Override
    public int substract(int a, int b) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + a + "," + b);
        // 通过目标对象来实现核心业务逻辑
        int addResult = target.substract(a, b);
        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }

    @Override
    public int multiply(int a, int b) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + a + "," + b);
        // 通过目标对象来实现核心业务逻辑
        int addResult = target.multiply(a, b);
        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }

    @Override
    public int divide(int a, int b) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + a + "," + b);
        // 通过目标对象来实现核心业务逻辑
        int addResult = target.divide(a, b);
        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }
}