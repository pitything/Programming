package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _3_懒汉式_线程不安全 {
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance2 = Singleton3.getInstance();
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

// 懒汉式_线程不安全
class Singleton3 {
    private Singleton3() {}

    private static Singleton3 instance;

    // 懒汉式：提供一个静态的公有方法，当使用到该方法时，才去创建 instance
    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}