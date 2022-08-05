package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _5_懒汉式_同步代码块 {
    public static void main(String[] args) {
        Singleton5 instance = Singleton5.getInstance();
        Singleton5 instance2 = Singleton5.getInstance();
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

// 懒汉式(线程安全，同步代码块)
class Singleton5 {
    private Singleton5() {}

    private static Singleton5 instance;

    // 懒汉式：提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized(Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}