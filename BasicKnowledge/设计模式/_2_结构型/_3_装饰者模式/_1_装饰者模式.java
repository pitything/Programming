package BasicKnowledge.设计模式._2_结构型._3_装饰者模式;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class _1_装饰者模式 {
    public static void main(String[] args) {

        // 装饰者模式下的订单： 2 份巧克力+一份牛奶的LongBlack
        // 1. 点一份 LongBlack
        Drink order = new LongBlack();
        System.out.println("费用 1 =" + order.cost());
        System.out.println("描述=" + order.getDes());

        // 2 .order加入一份牛奶
        order = new Milk(order);
        System.out.println("order加入一份牛奶 费用 =" + order.cost());
        System.out.println("order加入一份牛奶 描述 =" + order.getDes());

        // 3 .order加入一份巧克力
        order = new Chocolate(order);
        System.out.println("order加入一份牛奶 加入一份巧克力 费用 =" + order.cost());
        System.out.println("order加入一份牛奶 加入一份巧克力 描述 =" + order.getDes());

        // 3 .order加入一份巧克力
        order = new Chocolate(order);
        System.out.println("order加入一份牛奶 加入 2 份巧克力 费用 =" + order.cost());
        System.out.println("order加入一份牛奶 加入 2 份巧克力 描述 =" + order.getDes());

        System.out.println("===========================");
        Drink order2 = new DeCaf();
        System.out.println("order2 无因咖啡 费用 =" + order2.cost());
        System.out.println("order2 无因咖啡 描述 =" + order2.getDes());

        order2 = new Milk(order2);
        System.out.println("order2 无因咖啡 加入一份牛奶 费用 =" + order2.cost());
        System.out.println("order2 无因咖啡 加入一份牛奶 描述 =" + order2.getDes());
    }
}

abstract class Drink {
    public String des;// 描述
    private float price = 0.0f;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //计算费用的抽象方法
    //子类来实现
    public abstract float cost();
}

class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}

class DeCaf extends Coffee {
    public DeCaf() {
        setDes(" 无因咖啡 ");
        setPrice(1.0f);
    }
}

class Espresso extends Coffee {
    public Espresso() {
        setDes(" 意大利咖啡 ");
        setPrice(6.0f);
    }
}

class LongBlack extends Coffee {
    public LongBlack() {
        setDes("longblack");
        setPrice(5.0f);
    }
}

class ShortBlack extends Coffee {
    public ShortBlack() {
        setDes("shortblack");
        setPrice(4.0f);
    }
}

class Decorator extends Drink {
    private Drink obj;

    public Decorator(Drink obj) {//组合
        this.obj = obj;
    }

    @Override
    public float cost() {
        //getPrice 自己价格
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDes() {
        //obj.getDes() 输出被装饰者的信息
        return des + "" + getPrice() + "&&" + obj.getDes();
    }
}

//具体的Decorator， 这里就是调味品
class Chocolate extends Decorator {
    public Chocolate(Drink obj) {
        super(obj);
        setDes(" 巧克力 ");
        setPrice(3.0f);// 调味品 的价格
    }
}

class Milk extends Decorator {
    public Milk(Drink obj) {
        super(obj);
        setDes(" 牛奶 ");
        setPrice(2.0f);
    }
}

class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        setDes(" 豆浆 ");
        setPrice(1.5f);
    }
}