import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        // 要用Human接收，不能用SuperWoman
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(new SuperWoman());
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eatFood("watermelon");

        Wedding wedding = (Wedding) ProxyFactory.getProxyInstance(new Man1());
        wedding.startWedding();
    }
}

interface Human{
    String getBelief();
    void eatFood(String food);
}

/** 被代理类 */
class SuperWoman implements Human{
    @Override
    public String getBelief() {
        return "I believe i can fly..";
    }

    @Override
    public void eatFood(String food) {
        System.out.println("I am eating " + food);
    }
}

class ProxyFactory{
    // 返回一个代理类的对象
    public static Object getProxyInstance(Object obj){ // obj: 被代理类的对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                                      obj.getClass().getInterfaces(),
                                      new MyInvo(obj));
    }
}

class MyInvo implements InvocationHandler {
    // 被代理类对象
    private Object obj;

    public MyInvo(Object obj){
        this.obj = obj;
    }

    // 当通过代理类的a方法，会自动调用如下的invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy: 代理类对象；method：调用的方法；args：方法参数；obj：被代理类对象
        System.out.println("开始。。。");
        Object invoke = method.invoke(obj, args);
        System.out.println("结束。。。");
        return invoke;
    }
}
