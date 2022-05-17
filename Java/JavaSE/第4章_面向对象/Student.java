import java.util.Date;


public class Student extends Person2 {
    private String school;
    public Student(String name, int age, String s) {
//        super(name, age);
        school = s; }
    public Student(String name, String s) {
//        super(name);
        school = s; }
    // 子类构造器默认会去调用父类的无参构造器，如果父类没有无参构造器，则要显式调用对应的 super() 方法
    public Student(String s) {
        // 如果不写，则会编译出错: no super(),系统将调用父类无参数的构造器。
//        super(s);
        school = s;
    }
}

 class Person {
    private String name;
    private int age;
    private Date birthDate;
    public Person(String name, int age, Date d) {
        this.name = name;
        this.age = age;
        this.birthDate = d; }
    public Person(String name, int age) {
        this(name, age, null);
    }
    public Person(String name, Date d) {
        this(name, 30, d);
    }
    public Person(String name) {
        this(name, 30);
    }
//    public Person(){
//    }
}