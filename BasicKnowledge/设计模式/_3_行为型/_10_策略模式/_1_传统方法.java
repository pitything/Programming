package BasicKnowledge.设计模式._3_行为型._10_策略模式;

public class _1_传统方法 {
    public static void main(String[] args) {
        //测试
    }
}

abstract class Duck {
    public Duck() {}

    public abstract void display();//显示鸭子信息

    public void quack() {
        System.out.println("鸭子嘎嘎叫~~");
    }

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly() {
        System.out.println("鸭子会飞翔~~~");
    }
}

class PekingDuck extends Duck {
    @Override
    public void display() {
        System.out.println("~~北京鸭~~~");
    }

    //因为北京鸭不能飞翔，因此需要重写fly
    @Override
    public void fly() {
        System.out.println("北京鸭不能飞翔");
    }
}

class ToyDuck extends Duck {
    @Override
    public void display() {
        System.out.println("玩具鸭");
    }

    //需要重写父类的所有方法
    public void quack() {
        System.out.println("玩具鸭不能叫~~");
    }

    public void swim() {
        System.out.println("玩具鸭不会游泳~~");
    }

    public void fly() {
        System.out.println("玩具鸭不会飞翔~~~");
    }
}

class WildDuck extends Duck {
    @Override
    public void display() {
        System.out.println("这是野鸭 ");
    }
}