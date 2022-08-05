package BasicKnowledge.设计模式._1_创建型._1_单例模式;

public class _8_枚举 {
    public static void main(String[] args) {
        Singleton8 instance = Singleton8.INSTANCE;
        Singleton8 instance2 = Singleton8.INSTANCE;
        System.out.println(instance == instance2);//true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance 2 .hashCode=" + instance2.hashCode());
    }
}

//使用枚举，可以实现单例, 推荐
enum Singleton8 {
    INSTANCE;//属性
}