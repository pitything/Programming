package BasicKnowledge.设计模式._1_创建型._2_3_工厂模式;

public class _2_工厂方法模式 {
    public static void main(String[] args) {
        Pizza pizza = new GreekPizzaFactory().createPizza();
    }
}

abstract class FactoryMethod {
    //定义一个抽象方法，createPizza , 让各个工厂子类自己实现
    abstract Pizza createPizza();

    // 构造器
    public FactoryMethod() {
        Pizza pizza = null;
        // 订购披萨的类型
        pizza = createPizza();//抽象方法，由工厂子类完成
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

class GreekPizzaFactory extends FactoryMethod{
    @Override
    GreekPizza createPizza() {
        return new GreekPizza();
    }
}

class CheesePizzaFactory extends FactoryMethod{
    @Override
    CheesePizza createPizza() {
        return new CheesePizza();
    }
}

class PepperPizzaFactory extends FactoryMethod{
    @Override
    PepperPizza createPizza() {
        return new PepperPizza();
    }
}