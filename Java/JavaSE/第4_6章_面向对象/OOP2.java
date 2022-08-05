import java.util.Arrays;
import java.util.Date;

public class OOP2 {
    public static void main(String[] args) {
        Person1 person = new Student("leon", "hfut");
        System.out.println(person instanceof Student); // true
        // 对象可以调用static方法
        person.shout();

        // 装箱
        Integer a = new Integer(1);
        // 拆箱
        int b = a.intValue();
    }
}

class Student extends Person1 {
    public String school;
    // 子类构造器默认会去调用父类的无参构造器，如果父类没有无参构造器，则要显式调用对应的 super() 方法
    // 如果不写，则会编译出错: no super(),系统将调用父类无参数的构造器。
    public Student(String name, String s) {
        super(name);
        school = s;
    }
}

class Person1 extends Animals{
    public String name;
    public int age;
    public Date birthDate;
    public static double height;

    public Person1(String name) {
        this(name, 30);
    }
    public Person1(String name, Date d) {
        this(name, 30, d);
    }
    public Person1(String name, int age) {
        this(name, age, null);
    }

    public Person1(String name, int age, Date d) {
        this.name = name;
        this.age = age;
        this.birthDate = d;
    }

//    public Person1() {}

    public static void shout(){
        System.out.println("shout!!!");
    }
}

class Animals{
    public int liveTime;
}