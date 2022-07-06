import java.util.Arrays;

/**
 * 自定义枚举类
 */
class CusEnum{
    private final String SEASONNAME;//季节的名称
    private final String SEASONDESC;//季节的描述
    private CusEnum(String seasonName,String seasonDesc){
        this.SEASONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
        System.out.println(seasonName);
    }
    public static final CusEnum SPRING = new CusEnum("春", "春暖花开");
    public static final CusEnum SUMMER = new CusEnum("夏", "夏日炎炎");
    public static final CusEnum AUTUMN = new CusEnum("秋", "秋高气爽");
    public static final CusEnum WINTER = new CusEnum("冬", "白雪皑皑");
}

/**
 * 枚举类
 */
public enum TestEnum {
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "白雪皑皑");

    private final String SEASONNAME;//季节的名称
    private final String SEASONDESC;//季节的描述

    TestEnum(String seasonName, String seasonDesc){
        this.SEASONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
        System.out.println(seasonName);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("-------------枚举类---------------");
        System.out.println(Arrays.toString(TestEnum.values()));// 枚举数组：[SPRING, SUMMER, AUTUMN, WINTER]
        System.out.println(TestEnum.valueOf("SPRING")); // 通过名称返回实例：SPRING
        System.out.println(TestEnum.SPRING.SEASONNAME);// 秋天
        System.out.println(TestEnum.SPRING.name());    // 名称：SPRING
        System.out.println(TestEnum.SPRING.toString());// 名称，同toString()：SPRING
        System.out.println(TestEnum.SPRING.ordinal()); // 序号：0
        System.out.println(TestEnum.SPRING.equals(TestEnum.AUTUMN)); // 底层用==比较：false


        System.out.println(TestStrategyEnum.valueOf("STRING").getExcelValue());
    }
}


