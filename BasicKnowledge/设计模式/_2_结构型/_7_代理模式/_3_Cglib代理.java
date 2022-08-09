// package BasicKnowledge.设计模式._2_结构型._7_代理模式;
//
// import java.lang.reflect.InvocationHandler;
// import java.lang.reflect.Method;
// import java.lang.reflect.Proxy;
//
// public class _3_Cglib代理 {
//     public static void main(String[] args) {
//         //创建目标对象
//         TeacherDao2 target = new TeacherDao2();
//         //获取到代理对象，并且将目标对象传递给代理对象
//         TeacherDao2 proxyInstance = (TeacherDao2) new ProxyFactory(target).getProxyInstance();
//
//         //执行代理对象的方法，触发intecept 方法，从而实现 对目标对象的调用
//         System.out.println("res = " + proxyInstance.teach());
//     }
// }
//
// class TeacherDao2 {
//     public String teach() {
//         System.out.println("老师授课中 ， 我是cglib代理，不需要实现接口 ");
//         return "hello";
//     }
// }
//
// // class ProxyFactory implements MethodInterceptor {
// //     //维护一个目标对象
// //     private Object target;
// //
// //     //构造器，传入一个被代理的对象
// //     public ProxyFactory(Object target) {
// //         this.target = target;
// //     }
// //
// //     //返回一个代理对象: 是 target 对象的代理对象
// //     public Object getProxyInstance() {
// //         // 1. 创建一个工具类
// //         Enhancer enhancer = new Enhancer();
// //         // 2. 设置父类
// //         enhancer.setSuperclass(target.getClass());
// //         // 3. 设置回调函数
// //         enhancer.setCallback(this);
// //         // 4. 创建子类对象，即代理对象
// //         return enhancer.create();
// //     }
// //
// //     //重写 intercept 方法，会调用目标对象的方法
// //     @Override
// //     public Object intercept(Object arg0, Method method, Object[] args, Method Proxyarg3) throws Throwable {
// //         System.out.println("Cglib代理模式 ~~ 开始");
// //         Object returnVal = method.invoke(target, args);
// //         System.out.println("Cglib代理模式 ~~ 提交");
// //         return returnVal;
// //     }
// }