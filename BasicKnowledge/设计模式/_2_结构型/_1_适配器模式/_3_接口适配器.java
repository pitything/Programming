package BasicKnowledge.设计模式._2_结构型._1_适配器模式;

public class _3_接口适配器 {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            //只需要去覆盖我们 需要使用 接口方法
            @Override
            public void m1() {
                System.out.println("使用了 m1 的方法");
            }
        };
        absAdapter.m1();
    }
}

interface Interface4 {
    void m1();
    void m2();
    void m3();
    void m4();
}

//在AbsAdapter 我们将 Interface4 的方法进行默认实现
abstract class AbsAdapter implements Interface4 {
    //默认实现
    public void m1() {}
    public void m2() {}
    public void m3() {}
    public void m4() {}
}