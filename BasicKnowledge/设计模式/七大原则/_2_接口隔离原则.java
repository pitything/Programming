package BasicKnowledge.设计模式.七大原则;

public class _2_接口隔离原则 {
    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());
        C c = new C();
        c.depend1(new D());
        c.depend4(new D());
        c.depend5(new D());

        AA aa = new AA();
        aa.depend1(new BB());
        aa.depend2(new BB());
        aa.depend3(new BB());
        CC cc = new CC();
        cc.depend1(new DD());
        cc.depend4(new DD());
        cc.depend5(new DD());
    }
}

// 方式1：没有使用接口隔离原则代码
interface Interface1 {
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}

class B implements Interface1 {
    public void operation1() {
        System.out.println("B 实现了 operation1 ");
    }
    public void operation2() {
        System.out.println("B 实现了 operation2 ");
    }
    public void operation3() {
        System.out.println("B 实现了 operation3 ");
    }
    public void operation4() {
        System.out.println("B 实现了 operation4 ");
    }
    public void operation5() {
        System.out.println("B 实现了 operation5 ");
    }
}

class D implements Interface1 {
    public void operation1() {
        System.out.println("D 实现了 operation1 ");
    }
    public void operation2() {
        System.out.println("D 实现了 operation2 ");
    }
    public void operation3() {
        System.out.println("D 实现了 operation3 ");
    }
    public void operation4() {
        System.out.println("D 实现了 operation4 ");
    }
    public void operation5() {
        System.out.println("D 实现了 operation5 ");
    }
}

class A {//A类通过接口Interface1 依赖(使用)B类，但是只会用到 1 , 2 , 3 方法
    public void depend1(Interface1 i) {
        i.operation1();
    }
    public void depend2(Interface1 i) {
        i.operation2();
    }
    public void depend3(Interface1 i) {
        i.operation3();
    }
}

class C {//C 类通过接口Interface1 依赖(使用)D类，但是只会用到 1 , 4 , 5 方法
    public void depend1(Interface1 i) {
        i.operation1();
    }
    public void depend4(Interface1 i) {
        i.operation4();
    }
    public void depend5(Interface1 i) {
        i.operation5();
    }
}

// 方式2：使用接口隔离原则改进
// 接口 1
interface Interface11 {
    void operation1();
}
// 接口 2
interface Interface22 {
    void operation2();
    void operation3();
}

// 接口 3
interface Interface33 {
    void operation4();
    void operation5();
}

class BB implements Interface11, Interface22 {
    public void operation1() {
        System.out.println("B 实现了 operation1 ");
    }
    public void operation2() {
        System.out.println("B 实现了 operation2 ");
    }
    public void operation3() {
        System.out.println("B 实现了 operation3 ");
    }
}

class DD implements Interface11, Interface33 {
    public void operation1() {
        System.out.println("D 实现了 operation1 ");
    }
    public void operation4() {
        System.out.println("D 实现了 operation4 ");
    }
    public void operation5() {
        System.out.println("D 实现了 operation5 ");
    }
}

class AA {//A类通过接口Interface1 ,Interface2 依赖(使用)B类，但是只会用到 1 , 2 , 3 方法
    public void depend1(Interface11 i) {
        i.operation1();
    }
    public void depend2(Interface22 i) {
        i.operation2();
    }
    public void depend3(Interface22 i) {
        i.operation3();
    }
}

class CC {//C 类通过接口Interface1 ,Interface3 依赖(使用)D类，但是只会用到 1 , 4 , 5 方法
    public void depend1(Interface11 i) {
        i.operation1();
    }
    public void depend4(Interface33 i) {
        i.operation4();
    }
    public void depend5(Interface33 i) {
        i.operation5();
    }
}