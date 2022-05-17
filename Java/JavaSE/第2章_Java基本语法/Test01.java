public class Test01 {
    public static void main(String[] args) {
        float a = 1.111111111111F;
        System.out.print(a);

        short s = 3;
//        s = s + 3;  // 编译错误；因为这样会将short转为int
        s += 3;
    }
}