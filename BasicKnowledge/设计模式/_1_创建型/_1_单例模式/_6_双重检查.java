package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _6_双重检查 {
    public static void main(String[] args) {
        Singleton6 instance = Singleton6.getInstance();
        Singleton6 instance2 = Singleton6.getInstance();
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

class Singleton6 {
    private Singleton6() {}

    private static volatile Singleton6 instance;

    // 提供一个静态的公有方法，加入双重检查代码，解决线程安全问题, 解决懒加载问题，同时保证了效率, 推荐使用
    public static synchronized Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}