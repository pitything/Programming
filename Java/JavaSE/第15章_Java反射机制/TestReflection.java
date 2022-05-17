public class TestReflection {
    public static void main(String[] args) throws ClassNotFoundException {
        /** 获取类的四种方式 */
        testGetClass();
    }

    /** 获取类的四种方式 */
    static void testGetClass() throws ClassNotFoundException {
        Class class1 = SuperMan.class;
        Class<? extends SuperMan> class2 = new SuperMan(23, "leon", "man").getClass();
        Class<?> class3 = Class.forName("SuperMan");

//        ClassLoader classLoader = this.getClass().getClassLoader();
//        Class<? extends ClassLoader> class4 = classLoader.getClass();

    }
}



class SuperMan{
    private int age;
    private String name;
    public String gender;

    public SuperMan(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "SuperMan{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
