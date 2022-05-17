import java.util.ArrayList;
import java.util.List;

public class TestGenerics {
    public static void main (String[] args){
        GenericsStruction<String> genericsStruction = new GenericsStruction();
        System.out.println(genericsStruction.getT());
        GenericsStruction genericsStruction1 = new GenericsStruction();
        System.out.println(genericsStruction1.getT());




    }

    public static void test(){
        List<String> list1= null;
        List<Object> list2 = null;
        List<?> list = null;

        list = list1;

        print(list1);

    }

    public static void print(List<?> list){
        list.forEach(item -> System.out.println(item));
    }
}


class GenericsStruction<T>{
    T t;
//   static T t; 泛型不能用static修饰
    static String t1;

    GenericsStruction(){};

    GenericsStruction(T t){
        this.t = t;
    }

    public T getT(){
        return t;
    }

    public <T extends Animal> void setT(T t){
        System.out.println(t);
    }

}

class Creature{}

class Person2 extends Creature{}

class Animal extends Creature{}

class Dog extends Animal{}