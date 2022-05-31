public class BasicGrammar {
    public static void main(String[] args) {
        /** 整数类型 */
        byte a = 1;
        short b = 1;
        int c = 1;
        long d = 1111111111111111L;  // 如果小于int的范围，可以不加L，自动转换成long；如果大于就要加L或者l

        /** 浮点数类型 */
        float e = 1.1F;
        double f = 1.1;
//
//        short s = 3;
////        s = s + 3;  // 编译错误；因为这样会将short转为int
//        s += 3;
    }
}