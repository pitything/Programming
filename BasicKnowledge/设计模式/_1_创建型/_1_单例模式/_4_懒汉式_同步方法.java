package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _4_懒汉式_同步方法 {
    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance2 = Singleton4.getInstance();
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

// 懒汉式(线程安全，同步方法)
class Singleton4 {
    private Singleton4() {}

    private static Singleton4 instance;

    // 懒汉式：提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
    public static synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}