import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;
import java.util.Arrays;

public class TestReflection {
    @Test
    public void testGetClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /** 获取类的四种方式 */
        // 1.若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
        Class class1 = SuperMan.class;
        // 2.已知某个类的实例，调用该实例的getClass()方法获取Class对象
        Class<? extends SuperMan> class2 = new SuperMan(23, "leon").getClass();
        // 3.已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
        Class<?> class3 = Class.forName("SuperMan");
        // 4.通过类加载器获取
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> class4 = classLoader.loadClass("SuperMan");

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader parent = systemClassLoader.getParent();
        ClassLoader parent1 = parent.getParent();

        /** 常用方法 */
        // 1.返回类名：SuperMan
        System.out.println(class1.getName());
        // 2.新建一个对象实例，注意：要有无参构造器，否则报错 java.lang.InstantiationException
        Object o = class1.newInstance();
        // 3.返回当前Class对象的父类的Class对象
        Class superclass = class1.getSuperclass();
        // 4.获取当前Class对象的接口
        Class[] interfaces = class1.getInterfaces();
        // 5.返回该类的类加载器
        ClassLoader classLoader1 = class1.getClassLoader();
        // 6.返回一个包含某些Constructor对象的数组
        // getXX包括派生链上所有父类和本类的所有public方法（只能是public）
        // getDeclaredXX不包括父类的方法，专注于获取本类的所有方法（包括public和非public）
        System.out.println(Arrays.toString(class1.getConstructors()));
        System.out.println(Arrays.toString(class1.getDeclaredConstructors()));
        // 7.返回Field对象的一个数组
        System.out.println(Arrays.toString(class1.getFields()));
        System.out.println(Arrays.toString(class1.getDeclaredFields()));
        // 8.返回一个Method对象，此对象的形参类型为paramType
        System.out.println(Arrays.toString(class1.getMethods()));
        System.out.println(Arrays.toString(class1.getDeclaredMethods()));

        /** 可以有Class对象的类型 */
        // 1.class：外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类
        Class c1 = Object.class;
        // 2.interface：接口
        Class c2 = Comparable.class;
        // 3.[]：数组
        Class c3 = String[].class;
        Class c4 = int[][].class;
        // 4.enum：枚举
        Class c5 = ElementType.class;
        // 5.annotation：注解@interface
        Class c6 = Override.class;
        // 6.primitive type：基本数据类型
        Class c7 = int.class;
        // 7.void
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11); // true
    }

    @Test
    public void testGetClassStructure() throws NoSuchFieldException, NoSuchMethodException {
        Class<SuperMan> superManClass = SuperMan.class;
        // 1.获取实现的接口
        Class<?>[] interfaces = superManClass.getInterfaces();
        // 2.获取继承的父类
        Class<? super SuperMan> superclass = superManClass.getSuperclass();
        // 3.返回一个包含某些Constructor对象的数组
        // getXX包括派生链上所有父类和本类的所有public方法（只能是public）
        // getDeclaredXX不包括父类的方法，专注于获取本类的所有方法（包括public和非public）
        Constructor<?>[] constructors = superManClass.getConstructors();
        Constructor<?>[] declaredConstructors = superManClass.getDeclaredConstructors();
        // 4.返回注解
        Annotation[] annotations = superManClass.getAnnotations();
        // 5.返回Field对象的一个数组
        Field[] fields = superManClass.getFields();
        Field[] declaredFields = superManClass.getDeclaredFields();
        Field age = superManClass.getField("age");
        // 6.返回一个Method对象，此对象的形参类型为paramType
        Method[] methods = superManClass.getMethods();
        Method[] declaredMethods = superManClass.getDeclaredMethods();
        superManClass.getMethod("add", int.class, int.class);
        // 7.返回泛型
        TypeVariable<Class<SuperMan>>[] typeParameters = superManClass.getTypeParameters();
        // 8.返回所在包
        Package aPackage = superManClass.getPackage();

        // 获取修饰符：1：public；2：private；4：protected；0：default
        int modifiers = declaredMethods[0].getModifiers();
        // 获取方法名称
        String name = declaredMethods[0].getName();
        // 获取参数列表
        Class<?>[] parameterTypes = declaredMethods[0].getParameterTypes();
        // 获取返回值
        Class<?> returnType = declaredMethods[0].getReturnType();
        // 获取异常
        Class<?>[] exceptionTypes = declaredMethods[0].getExceptionTypes();
    }

    @Test
    public void testInvokeMedFie() throws Exception {
        Class<SuperMan> superManClass = SuperMan.class;
        /** 通过反射创建对象 */
        // 1.新建一个对象实例，注意：要有无参构造器，否则报错 java.lang.InstantiationException
        SuperMan superMan = superManClass.newInstance();
        // 2.反射获取类的构造器创建对象
        Constructor<SuperMan> constructor = superManClass.getDeclaredConstructor(int.class, String.class);
        SuperMan ll = constructor.newInstance(13, "ll");

        /** 用反射调用属性 */
        // 成员属性
        Field age = superManClass.getDeclaredField("age");
            // 设置为可修改，因为private属性不可以修改，会报错：java.lang.IllegalAccessException
        age.setAccessible(true);
        age.get(ll);
        age.set(ll, 24);
        // 静态属性
        Field str = superManClass.getDeclaredField("str");
        str.get(null);
        str.set(SuperMan.class, "ssss");

        /** 用反射调用方法 */
        // 成员方法
        Method add = superManClass.getMethod("add", int.class, int.class);
        int invoke = (int)add.invoke(ll, 3, 2);
        // 静态方法
        Method multiply = superManClass.getMethod("multiply", int.class, int.class);
        Object invoke1 = multiply.invoke(SuperMan.class, 3, 2);
    }
}

class SuperMan{
    private int age;
    public String name;
    public static String str = "身体乳";

    public SuperMan(){}

    SuperMan(int age) {
        this.age = age;
    }

    public SuperMan(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SuperMan{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int add(int a, int b){
        return a + b;
    }
    public static int multiply(int a, int b){
        return a * b;
    }
}