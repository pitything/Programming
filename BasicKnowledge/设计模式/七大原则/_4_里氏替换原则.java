package BasicKnowledge.设计模式.七大原则;

public class _4_里氏替换原则 {
    public static void main(String[] args) {
        A_4_里氏替换原则 a = new A_4_里氏替换原则();
        System.out.println(" 11 - 3 = " + a.func1(11, 3));
        System.out.println(" 1 - 8 = " + a.func1(1, 8));
        System.out.println("-----------------------");
        B_4_里氏替换原则 b = new B_4_里氏替换原则();
        System.out.println(" 11 - 3 = " + b.func1(11, 3));//这里本意是求出 11 - 3
        System.out.println(" 1 - 8 = " + b.func1(1, 8));// 1 - 8
        System.out.println(" 11 + 3 + 9 = " + b.func2(11, 3));
    }
}

// A类
class A_4_里氏替换原则 {
    // 返回两个数的差
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

// B类继承了A
// 增加了一个新功能：完成两个数相加,然后和 9 求和
class B_4_里氏替换原则 extends A_4_里氏替换原则 {
    // 重写了A类的方法, 可能是无意识
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}


class Liskov {
    public static void main(String[] args) {
        A_Base a = new A_Base();
        System.out.println(" 11 - 3 =" + a.func1(11, 3));
        System.out.println(" 1 - 8 =" + a.func1(1, 8));
        
        System.out.println("-----------------------");
        B_Base b = new B_Base();
        //因为B类不再继承A类，因此调用者，不会再func1 是求减法
        //调用完成的功能就会很明确
        System.out.println(" 11 + 3 =" + b.func1(11, 3));//这里本意是求出 11 + 3
        System.out.println(" 1 + 8 =" + b.func1(1, 8));// 1 + 8
        System.out.println(" 11 + 3 + 9 =" + b.func2(11, 3));

        //使用组合仍然可以使用到A类相关方法
        System.out.println(" 11 - 3 =" + b.func3(11, 3));// 这里本意是求出 11 - 3
    }
}

//创建一个更加基础的基类
abstract class Base {
    //把更加基础的方法和成员写到Base类
    abstract int func1(int num1, int num2);
}

//A类
class A_Base extends Base {
    // 返回两个数的差
    @Override
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

//B类继承了A，增加了一个新功能：完成两个数相加,然后和 9 求和
class B_Base extends Base {
    //如果B需要使用A类的方法,使用组合关系
    private A_Base a = new A_Base();

    //这里，重写了A类的方法, 可能是无意识
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    //我们仍然想使用A的方法
    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}