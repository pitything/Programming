package BasicKnowledge.设计模式._2_结构型._7_代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class _2_动态代理 {
    public static void main(String[] args) {
        //创建目标对象
        ITeacherDao1 target = new TeacherDao1();

        //给目标对象，创建代理对象, 可以转成  ITeacherDao1 
        ITeacherDao1 proxyInstance = (ITeacherDao1) new ProxyFactory(target).getProxyInstance();

        //proxyInstance= class com.sun.proxy.$Proxy 0 内存中动态生成了代理对象
        System.out.println("proxyInstance=" + proxyInstance.getClass());

        //通过代理对象，调用目标对象的方法
        proxyInstance.teach();
        proxyInstance.sayHello("tom");
    }
}

//接口
interface ITeacherDao1 {
    void teach();// 授课方法
    void sayHello(String name);
}

class TeacherDao1 implements ITeacherDao1 {
    @Override
    public void teach() {
        System.out.println("老师授课中");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("hello" + name);
    }
}

class ProxyFactory {
    //维护一个目标对象 , Object 
    private Object target;

    //构造器 ， 对target 进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象 生成一个代理对象
    public Object getProxyInstance() {
        /**
         * ClassLoader loader ： 指定当前目标对象使用的类加载器, 获取加载器的方法固定
         * Class<?>[] interfaces: 目标对象实现的接口类型，使用泛型方法确认类型
         * InvocationHandler h: 事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行的目标对象方法作为参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK代理开始~~");
                        //反射机制调用目标对象的方法
                        Object returnVal = method.invoke(target, args);
                        System.out.println("JDK代理提交");
                        return returnVal;
                    }
                });
    }
}