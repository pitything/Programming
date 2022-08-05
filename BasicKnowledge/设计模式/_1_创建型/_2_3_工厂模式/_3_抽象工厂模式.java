package BasicKnowledge.设计模式._1_创建型._2_3_工厂模式;

public class _3_抽象工厂模式 {
    public static void main(String[] args) {
        Pizza pizza = new BigFactory().createPizza("cheese");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

class BigCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("BigCheesePizza");
        System.out.println(" BigCheesePizza 准备");
    }
}
class BigPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BigPepperPizza");
        System.out.println(" BigPepperPizza 准备");
    }
}

class MiddleCheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("MiddleCheesePizza");
        System.out.println("MiddleCheesePizza 准备");
    }
}
class MiddlePepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("MiddlePepperPizza");
        System.out.println("MiddlePepperPizza 准备");
    }
}

class SmallCheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("SmallCheesePizza");
        System.out.println("SmallCheesePizza 准备");
    }
}
class SmallPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("SmallPepperPizza");
        System.out.println("SmallPepperPizza 准备");
    }
}

//一个抽象工厂模式的抽象层(接口)
abstract class AbsFactory {
    //让下面的工厂子类来 具体实现
    abstract Pizza createPizza(String orderType);
}

//这是工厂子类
class BigFactory extends AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BigCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BigPepperPizza();
        }
        return pizza;
    }
}

class MiddleFactory extends AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new MiddleCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new MiddlePepperPizza();
        }
        return pizza;
    }
}

class SmallFactory extends AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new SmallCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new SmallPepperPizza();
        }
        return pizza;
    }
}