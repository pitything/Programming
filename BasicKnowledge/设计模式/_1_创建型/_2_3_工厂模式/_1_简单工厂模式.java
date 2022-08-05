package BasicKnowledge.设计模式._1_创建型._2_3_工厂模式;

public class _1_简单工厂模式 {
    public static void main(String[] args) {
        Pizza pizza = new PizzaFactory().createPizza("greek");
    }
}

// Pizza 抽象类
abstract class Pizza {
    protected String name;

    public abstract void prepare();
    public void bake() {
        System.out.println(name + " baking;");
    }
    public void cut() {
        System.out.println(name + " cutting;");
    }
    public void box() {
        System.out.println(name + " boxing;");
    }
    public void setName(String name) {
        this.name = name;
    }
}

class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 希腊披萨 准备 ");
    }
}

class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 奶酪披萨 准备 ");
    }
}

class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 胡椒披萨 准备 ");
    }
}

//简单工厂类
class PizzaFactory {
    //更加orderType 返回对应的Pizza   对象
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;

        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}