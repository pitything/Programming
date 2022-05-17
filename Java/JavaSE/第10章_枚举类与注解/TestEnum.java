import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public enum TestEnum {
    SPRING("aaa"), SUMMER("bbb"), AUTUER("ccc"), WINTER("ddd");

    private final String tttt;

    TemporalAdjuster temporalAdjusters;

    TestEnum(String name) {

        this.tttt = name;
    }

    public static void main(String[] args) {
        System.out.println(TestEnum.AUTUER.name());
        System.out.println(TestEnum.SPRING.ordinal());
        System.out.println(TestEnum.WINTER.ordinal());
        System.out.println(Arrays.toString(TestEnum.values()));
    }
}

/**
 * 自定义枚举类
 */
class CusEnum{
    private final String name;
    private final String value;

    private CusEnum(String name, String value){
        this.name = name;
        this.value = value;
    }

    public static final CusEnum TEST1 = new CusEnum("TEST1", "TEST1");
    public static final CusEnum TEST2 = new CusEnum("TEST2", "TEST2");
    public static final CusEnum TEST3 = new CusEnum("TEST3", "TEST3");
}
