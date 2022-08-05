package 第4_6章_面向对象;

//构造块就是非静态代码块
//执行顺序：父类静态代码块（只执行一次，并且是在main之前执行）>子类静态代码块>父类构造块>父类构造方法>子类构造块>子类构造方法
public class OOP3 {
    public static void main(String[] args) {
        A s = new A();
        s.Info();
        int n=s.an;
        System.out.println(n);

        /** 静态内部类 */
        OuterClass.StaticClass staticClass = new OuterClass.StaticClass();
        /** 成员内部类 */
        OuterClass outerClass = new OuterClass();
        OuterClass.Inner inner = outerClass.new Inner();



    }
}
class B implements BB{
    public   int  age;
    public  String name;
    public   int an;
    //构造方法
    public  B(int age,String name){
        this.name=name;
        this.age=age;
        System.out.println("父类构造方法");
    }
    //静态代码块
    static {
        System.out.println("父类静态代码块");
    }
    //构造快
    {
        System.out.println("父类构造块");
    }
    //普通方法
    public  void  Info(){
        System.out.println("姓名："+this.name+"\t"+"年龄："+this.age+"\t"+"未知："+an);
    }

    // 默认方法可以不重写
    @Override
    public void shout() {
        BB.super.shout();
    }
}
class  A   extends  B{
    public A() {
        super(12,"nihao");
        System.out.println("子类的构造方法");
    }
    {
        System.out.println("子类构造块");
    }
    static {
        System.out.println("子类静态代码块");
    }
}

abstract class AA{}

interface BB{
    default void shout(){
        System.out.println("shout");
    }
}

/** 内部类 */
class OuterClass{
    // 静态内部类
    static class StaticClass{
        public void testStaticClass(){
            System.out.println("testStaticClass!!!");
        }
    }

    // 成员内部类
    class Inner{
        public void testInner(){
            System.out.println("testInner!!!");
        }
    }

    // 局部内部类
    public void testFuncClass(String str){
        class FuncClass{
            public void funcClass1(){
                System.out.println(str + "funcClass1!!!");
            }
        }

        FuncClass funcClass = new FuncClass();
        funcClass.funcClass1();
    }

    // 匿名内部类
    public void test(final int i) {
        new Int4AnonyClass() {
            public void method() {
                for (int j = 0; j < i; j++) {
                    System.out.println("匿名内部类");
                }
            }
        }.method();
    }
}

// 匿名内部类必须继承或者实现一个抽象类或接口
interface Int4AnonyClass{
    void method();
}