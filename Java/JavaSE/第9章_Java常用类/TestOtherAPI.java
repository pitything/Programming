import java.util.Arrays;

public class TestOtherAPI {
    public static void main(String[] args) {
        /** System类 */
        System.out.println(System.currentTimeMillis());
        Integer[] src = new Integer[]{1, 2, 3};
        Integer[] desc = new Integer[]{3, 4, 5};
        System.arraycopy(src, 1, desc, 0, 2); //数组复制： [2, 3, 5]
        System.out.println(Arrays.toString(desc));
        System.exit(0);
    }
}
