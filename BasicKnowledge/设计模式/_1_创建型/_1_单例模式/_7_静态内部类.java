package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _7_静态内部类 {
    public static void main(String[] args) {
        Singleton7 instance = Singleton7.getInstance();
        Singleton7 instance2 = Singleton7.getInstance();
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

// 静态内部类完成， 推荐使用
class Singleton7 {
    private Singleton7() {}

    //写一个静态内部类,该类中有一个静态属性 Singleton
    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    //提供一个静态的公有方法，直接返回Singleton7Instance.INSTANCE
    public static synchronized Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}