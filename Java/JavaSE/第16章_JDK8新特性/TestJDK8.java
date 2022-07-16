import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestJDK8 {
    @Test
    public void testLambda(){
        Runnable runnable1 = new Runnable(){
            @Override
            public void run() {
                System.out.println("runnable1");
            }
        };
        runnable1.run();
        Runnable runnable2 = () -> System.out.println("runnable2");
        runnable2.run();


        Comparator<Integer>  comparator1 = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        comparator1.compare(2, 3);
        Comparator<Integer>  comparator2 = (o1, o2) -> 0;
        comparator2.compare(3, 4);
    }

    @Test
    public void testFuncConstruRef(){
        /** 方法引用 */
        Consumer<Integer> consumer1 = x -> System.out.println(x);
        Consumer<Integer> consumer2 = System.out::println;
        consumer1.accept(1);
        consumer2.accept(2);

        Comparator<Integer> comparator1 = (x, y) -> (x > y) ? 1 : -1;
        Comparator<Integer> comparator2 = Integer::compare;
        Comparator<Integer> comparator3 = Integer::compareTo;
        System.out.println(comparator1.compare(2, 3));
        System.out.println(comparator2.compare(2, 3));
        System.out.println(comparator3.compare(2, 3));

        /** 构造器引用 */
        Consumer<String> consumer3 = x -> new Integer(x);
        Consumer<String> consumer4 = Integer::new;
    }

    @Test
    public void testOptional() throws Throwable {
        Person person = new Person();
        // Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
        Optional<Person> optional1 = Optional.of(person);
        // Optional.empty() : 创建一个空的 Optional 实例
        Optional<Object> empty = Optional.empty();
        // Optional.ofNullable(T t)：t可以为null
        Optional<Person> optional2 = Optional.ofNullable(person);
        // boolean isPresent() : 判断是否包含对象
        boolean present = optional2.isPresent();
        // void ifPresent(Consumer<? super T> consumer) ：如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。
        optional2.ifPresent(System.out::println);
        // T get(): 如果调用对象包含值，返回该值，否则抛异常
        Person person2 = optional2.get();
        // T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象。
        optional2.orElse(new Person());
        // T orElseGet(Supplier<? extends T> other) ：如果有值则将其返回，否则返回由Supplier接口实现提供的对象。
        optional2.orElseGet(() -> new Person());
        // T orElseThrow(Supplier<? extends X> exceptionSupplier) ：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常
        optional2.orElseThrow((Supplier<Throwable>) () -> new Exception("值不能为空"));
        optional2.orElseThrow(() -> new Exception("值不能为空"));

        // 示例
        person = new Person(null, 8800, 39, "male", "New York");
//        person = null;
        if(person != null){
            String name = person.getName();
            if(name != null){
                System.out.println(name.length());
            }else {
                System.out.println(0);
            }
        }else {
            System.out.println(0);
        }
        // 简化
        Integer integer = Optional.ofNullable(person).map(p -> p.getName()).map(name -> name.length())
                .orElse(0);
        System.out.println(integer);
    }

    /** Stream的创建 */
    @Test
    public void testCreateStreamApi(){
        // 1.通过集合创建
        List<Integer> list1 = new ArrayList<Integer>(){{add(3);add(1);add(2);}};
        // 创建串行流
        Stream<Integer> stream1 = list1.stream();
        // 创建并行流
        Stream<Integer> parallelStream1 = list1.parallelStream();
        Stream<Integer> parallelStream2 = stream1.parallel();

        // 2.通过数组创建
        int[] array2 = new int[]{3, 1, 2};
        IntStream stream2 = Arrays.stream(array2);

        // 3.使用Stream的静态方法：of()、iterate()、generate()
        Stream.of(list1);
        Stream.of(array2);
        Stream.of(3, 1, 2);
        Stream.iterate(0, x -> x + 2);
        Stream.generate(Math::random);
    }

    static List<Person> personList = new ArrayList<Person>();
    static {
        personList.add(new Person("Tom", 8900, 27, "male", "New York"));
        personList.add(new Person("Jack", 7000, 26, "male", "Washington"));
        personList.add(new Person("Lily", 7007, 23, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 29, "female", "New York"));
        personList.add(new Person("Owen", 9500, 43, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 32, "female", "New York"));
        personList.add(new Person("Alisa", 8800, 39, "male", "New York"));
    }

    /** Stream流的使用：中间操作 */
    @Test
    public void testUseStreamApiMid(){
        /** 筛选(filter\distinct) */
        System.out.println("----------filter----------");
        // filter：salary < 8000的所有人
        personList.stream().filter(person -> person.getSalary() < 8000).forEach(System.out::println);
        // distinct：根据equals方法去重
        personList.stream().distinct().forEach(System.out::println);

        /** 分割与组合(limit\skip\concat) */
        System.out.println("----------limit----------");
        // limit：取前4个人
        personList.stream().limit(4).forEach(System.out::println);
        System.out.println("----------skip--------");
        // skip：跳过前4个人
        personList.stream().skip(4).forEach(System.out::println);
        System.out.println("----------concat----------");
        // concat：取前2个，加上跳过前5个的所有人
        Stream.concat(personList.stream().limit(2), personList.stream().skip(5)).forEach(System.out::println);

        /** 映射(map\flatMap\mapToXXX) */
        System.out.println("----------map----------");
        // map：每个人的工资加1000
        // 不改变原来list
        personList.stream().map(person -> {
            Person newPerson = new Person(person.getName(), 0, 0, null, null);
            newPerson.setSalary(person.getSalary() + 1000);
            return newPerson;
        }).forEach(System.out::println);
        // 改变原来list
        personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).forEach(System.out::println);
        // 返回所有员工名称
        personList.stream().map(Person::getName).forEach(System.out::println);

        System.out.println("----------flatMap----------");
        // flatMap：将每个person分成一个stream，返回的每个person操作后的流，再合并
        personList.stream().flatMap(person -> {
            person.setSalary(person.getSalary() + 500);
            return Arrays.stream(new Person[]{person});
        }).forEach(System.out::println);

        System.out.println("----------mapToXXX----------");
        // mapToXXX：返回所有人salary + 1000后的int数组
        personList.stream().mapToInt(person -> person.getSalary() + 1000).forEach(System.out::println);

        /** 排序(sorted) */
        System.out.println("----------sorted----------");
        // 自然排序：Person要实现Comparable接口，按照salary升序排列
        personList.stream().sorted().forEach(System.out::println);
        // 自定义排序：重写Comparator的compare方法，按照salary降序排列
        personList.stream().sorted((o1, o2) -> o1.getSalary() > o2.getSalary() ? -1 : 1).forEach(System.out::println);
    }


    /** Stream流的使用：终端操作 */
    @Test
    public void testUseStreamApiTer(){
        /** 遍历(forEach) */
        personList.stream().forEach(System.out::println);

        /** 匹配(allMatch\anyMatch\noneMatch) */
        // allMatch：是否personList中所有人都叫Jack
        System.out.println(personList.stream().allMatch(person -> "Jack".equals(person.getName())));
        // anyMatch：是否personList中至少一个人叫Jack
        System.out.println(personList.stream().anyMatch(person -> "Jack".equals(person.getName())));
        // noneMatch：是否personList中没有人都叫Jack
        System.out.println(personList.stream().noneMatch(person -> "Jack".equals(person.getName())));

        /** 查找(findFirst\findAny) */
        // findFirst：匹配第一个
        System.out.println(personList.stream().findFirst().get());
        // findAny：匹配任意（适用于并行流）
        System.out.println(personList.stream().findAny().get());

        /** 聚合(max\min\count) */
        // max：工资最高的
        System.out.println(personList.stream().max((o1, o2) -> o1.getSalary() > o2.getSalary() ? 1 : -1).get());
        // min：工资最低的
        System.out.println(personList.stream().min((o1, o2) -> o1.getSalary() > o2.getSalary() ? 1 : -1).get());
        // count：员工总个数
        System.out.println(personList.stream().count());

        /** 归约(reduce) */
        // 求公司员工总薪资
        System.out.println(personList.stream().reduce((person, person2) ->
                new Person("", person.getSalary() + person2.getSalary(), 0, "", "")
        ).get().getSalary());
        // 求最大薪资员工
        System.out.println(personList.stream().reduce((person, person2) ->
                person.getSalary() > person2.getSalary() ? person : person2
        ).get());

        /** 收集 */
        /*** 归集(toList\toSet\toMap) */
        // toList：转化为list
        List<Person> personList2 = personList.stream().collect(Collectors.toList());
        // toSet：转化为set
        Set<Person> personSet2 = personList.stream().collect(Collectors.toSet());
        // toMap：转化为map
        Map<Integer, Person> personMap = personList.stream().collect(Collectors.toMap(Person::getSalary, person -> person));

        /*** 统计(counting\averagingInt\maxBy\summingInt\summarizingInt) */
        // 计数：counting
        Long counting = personList.stream().collect(Collectors.counting());
        // 平均值：averagingInt、averagingLong、averagingDouble
        Double averagingInt = personList.stream().collect(Collectors.averagingInt(Person::getSalary));
        // 最值：maxBy、minBy
        Person maxBy = personList.stream().collect(Collectors.maxBy((person, person2) ->
                person.getSalary() > person2.getSalary() ? 1 : -1
        )).get();
        // 求和：summingInt、summingLong、summingDouble
        int summingInt = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
        IntSummaryStatistics summarizingInt = personList.stream().collect(Collectors.summarizingInt(Person::getSalary));

        /*** 分组(partitioningBy/groupingBy) */
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> partitioningBy = personList.stream().collect(Collectors.partitioningBy(p -> p.getSalary() > 8000));
        // 将员工按照性别分组
        Map<String, List<Person>> groupingBySex = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按照性别分组，在按照地区分组
        Map<String, Map<String, List<Person>>> groupingBySexArea = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        // 将员工按照性别分组，求男女工资和
        Map<String, IntSummaryStatistics> salaryGroupingBySex = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex, Collectors.summarizingInt(Person::getSalary)));

        /*** 接合(join) */
        // join：逗号拼接所有员工名字
        String join = personList.stream().map(Person::getName).collect(Collectors.joining(","));

        /*** 归约(reducing) */
        // 每个员工减去5000的起征点后的薪资之和
        Integer reducing = personList.stream().collect(Collectors
                .reducing(0, Person::getSalary, (x, y) -> x + y - 5000));
    }
}

class Person implements Comparable{
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    public Person(){}

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.getSalary() > ((Person)o).getSalary() ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

class TestJDK888 implements Name1, Name2{
    @Override
    public void getName() {
        Name2.super.getName();
    }
}

interface Name1{
    default void getName(){
        System.out.println("name1");
    }
    static void testStaticFunc(){
        System.out.println("static");
    }
}
interface Name2{
    default void getName(){
        System.out.println("Name2");
    }
}