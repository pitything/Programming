package BasicKnowledge.设计模式._3_行为型._10_策略模式;

class _2_策略模式 {
    public static void main(String[] args) {
        WildDuck11 wildDuck1 = new WildDuck11();
        wildDuck1.fly();//

        ToyDuck11 toyDuck1 = new ToyDuck11();
        toyDuck1.fly();

        PekingDuck11 pekingDuck1 = new PekingDuck11();
        pekingDuck1.fly();
        //动态改变某个对象的行为, 北京鸭 不能飞
        pekingDuck1.setFlyBehavior(new NoFlyBehavior());
        System.out.println("北京鸭的实际飞翔能力");
        pekingDuck1.fly();
    }
}

abstract class Duck1 {
    //属性, 策略接口
    FlyBehavior flyBehavior;

    public Duck1() {}

    public abstract void display();//显示鸭子信息

    public void fly() {
        //改进
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}

class PekingDuck11 extends Duck1 {
    //假如北京鸭可以飞翔，但是飞翔技术一般
    public PekingDuck11() {
        flyBehavior = new BadFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("~~北京鸭~~~");
    }
}

class ToyDuck11 extends Duck1 {
    public ToyDuck11() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("玩具鸭");
    }
}

class WildDuck11 extends Duck1 {
    //构造器，传入FlyBehavor 的对象
    public WildDuck11() {
        flyBehavior = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是野鸭 ");
    }
}

interface FlyBehavior {
    void fly();// 子类具体实现
}

class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞翔技术一般 ");
    }
}

class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞翔技术高超 ~~~");
    }
}

class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞翔 ");
    }
}