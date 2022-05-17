import java.util.*;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        variableNumParam("aaa", "bbb");
        TestClass1 testClass1 = new TestClass1();
        for(int i=0; i<1; i++){
            TestClass1 m = new TestClass1();//这里本次循环完，本次创建的对象就成为垃圾了
            System.out.println("创建第" + (i+1) + "的对象：" + m);
        }

        List<String> list = new ArrayList<>();

        //通知垃圾回收机制来收集垃圾
        System.gc();

        //为了延缓程序结束
//        for(int i=0; i<10; i++){
//            Thread.sleep(1);
//            System.out.println("程序在继续....");
//        }

    }


    /**
     * 可变参数方法
     * @param aaa
     * @param strs
     */
    static void variableNumParam(String aaa, String... strs){
        System.out.println(Arrays.toString(strs));
    }
    static void variableNumParam(Double[] strs){
        System.out.println(Arrays.toString(strs));
    }
}

class TestClass1{
    // 静态方法不能继承
    public static int aaa(){
        return 0;
    }
    public int bbb(){
        return 0;
    }

    @Override
    //这个方法是垃圾回收机制在回收它的对象时，自动调用，理解成对象留临终遗言的方法
    public void finalize(){
        System.out.println("轻轻的我走了.....");
    }
}

class TestClass2 extends TestClass1{
    @Override
    public int bbb(){
        return 2;
    }
}


abstract class AbsAAA{
    public static final String AAA = "aaa";
    abstract int getInt();
    abstract int getInt(int a);
}

abstract class BBB extends AbsAAA{
    @Override
    int getInt() {
        return 0;
    }
}

interface IntBBB{
    default int getInt() {
        return 0;
    }
}

interface IntEEE{
    default int getInt() {
        return 0;
    }
}

interface Intccc extends IntBBB {
    @Override
    int getInt();
}

interface Intddd extends IntBBB {
    @Override
    int getInt();
}

class TestInterface implements IntEEE, IntBBB{
    @Override
    public int getInt() {
        return IntEEE.super.getInt();
    }
}

