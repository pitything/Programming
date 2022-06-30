public class BasicGrammar {
    public static void main(String[] args) {
        /** 整数类型 */
        byte a = 1;
        short b = 1;
        int c = 1;
        long d = 21000000000L;  // 如果小于int的范围，可以不加L，自动转换成long；如果大于就要加L或者l

        /** 浮点数类型 */
        float e = 1.1F;
        float e1 = 9991.1111111F; // 精确到7位有效数字
        System.out.println("e1: " + e1);
        double f = 1111.1111111111176; // 有效数字位为15 – 16位
        System.out.println("f: " + f);

        /** 字符型 */
        char g = 122;
        System.out.println("g: " + (g ));

        /** 布尔类型 */
        boolean t = true;
        boolean ff = false;

        /**
         * 运算符
         */
        /** 位运算符 */
        System.out.println("----------位运算符-------------");
        System.out.println((15) << 2);// 60
        System.out.println((-15) << 2);// -60
        System.out.println((15) >> 2);// 3
        System.out.println((-15) >> 2);// -4
        System.out.println((15) >>> 2);// 3
        System.out.println((-15) >>> 2);// 1073741820
        System.out.println(6 & 3);// 2
        System.out.println(6 | 3);// 7
        System.out.println(6 ^ 3);// 5
        System.out.println(~3);// -4

        /**
         * 程序流程
         */
        /** 分支结构 */
        /** if...else */
        if(1 == 2){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

        /*** switch-case */
        String season = "summer";
        switch (season) {
            case "spring":
                System.out.println("春暖花开");
                break;
            case "summer":
                System.out.println("夏日炎炎");
                break;
            case "autumn":
                System.out.println("秋高气爽");
                break;
            case "winter":
                System.out.println("冬雪皑皑");
                break;
            default:
                System.out.println("季节输入有误");
                break;
        }

        /** 循环结构 */
        /** for循环 */
        for(int i = 0; i < 100; i++){
            System.out.println(i);
        }
        /** while循环 */
        int j = 0;
        while(j < 20){
            System.out.println(j);
            j ++;
        }
        /** do...while循环 */
        int k = 40;
        do{
            System.out.println(k);
            k++;
        }while(k < 70);

        /** break使用 */
        System.out.println("------------break使用------------");
        AA:
        for(int i = 0; i < 10; i++){
            for(int i2 = 0; i2 < 5; i2++){
                System.out.println(i + i2);
                if(i == 5){
                    break;
                }
                if(i == 7){
                    break AA;
                }
            }
        }
        System.out.println("------------continue使用------------");
        BB:
        for(int i = 0; i < 10; i++){
            for(int i2 = 0; i2 < 5; i2++){
                if(i == 5){
                    continue ;
                }
                if(i == 7){
                    continue BB;
                }
                System.out.println(i + i2);
            }
        }

    }
}