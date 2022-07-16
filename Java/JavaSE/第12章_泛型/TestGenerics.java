import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {
    @Test
    public void testGenerics(){
        /** 泛型 */
        ArrayList<String> list = new ArrayList<>();

        /** 自定义泛型 */
        /*** 泛型类/泛型接口*/
        MyGenericsInt<String> myGenericsInt;
        MyGenericsClass<String> myGenericsClass;
        MyGenericsClass myGenericsClass1;

        // 泛型不同的引用不能相互赋值
        ArrayList<String> list1 = new ArrayList<String>(){{add("aa");add("bb");}};
        ArrayList<?> list3 = new ArrayList<>();
        list3 = list1;
//        list3.add("aaa");
        list3.add(null);

    }

    /*** 泛型方法 */
    public <K> String getK(K k) throws Exception{
        return "k";
    }
}

interface MyGenericsInt<T>{}

class MyGenericsClass<T>{
    T t;

//   static T t; 泛型不能用static修饰
//    static T t1;
//    public static T returnT(T tt){
//        return tt;
//    }

    MyGenericsClass(){};

    public <T extends Animal> void setT(T t) throws Exception{
        System.out.println(t);
    }
}

// 1.子类不保留父类泛型：擦除泛型
class MyGenericsClass2 extends MyGenericsClass{}
// 2.子类不保留父类泛型：定义新泛型
class MyGenericsClass3<T> extends MyGenericsClass{}
// 3.子类保留父类泛型
class MyGenericsClass4<T> extends MyGenericsClass<T>{}
// 4.子类保留父类泛型：可拓展
class MyGenericsClass5<K extends T, T> extends MyGenericsClass<T>{}

class Creature{
}
class Animal extends Creature{}