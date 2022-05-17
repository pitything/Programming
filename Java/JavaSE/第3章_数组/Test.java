import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String [] args) {
        /** 二维数组 */
        int[][] aa = new int[1][];
//        int [][] aa = new int[][1];   // 编译错误

        /* 特殊写法:x为一维数组；y为二维数组 */
        int[] x, y[];
        x = new int[3];
        y = new int[1][];

        /** 打印杨辉三角 */
        printYanghui(10);

        /** Arrays类常用方法 */
        List<String> strlist1 = new ArrayList<String>() {{
            add("aa");
            add("bb");
            add("cc");
        }};
        List<String> strlist2 = new ArrayList<String>() {{
            add("中国");
            add("日本");
            add("美国");
        }};
        int[] intArr = new int[]{1, 3, 2, 3, 41, 53, 23, 45, 23, 32};
        String[] strArr = new String[]{"中国","日本","美国"};

        // int数组转list
        List<Integer> intArrList = Arrays.stream(intArr).boxed().collect(Collectors.toList());
        System.out.println(intArrList);
        // String 数组转list
        List<String> strArrList = new ArrayList<>(Arrays.asList(strArr));
        List<String> strArrList2 = Arrays.stream(strArr).collect(Collectors.toList());
        System.out.println("strArrList: " + strArrList);
        System.out.println("strArrList2: " + strArrList2);

        // list转数组
        String[] listArr = strlist1.toArray(new String[strlist1.size()]);
        System.out.println(Arrays.toString(listArr));

        // 排序，打印
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));
        Arrays.sort(intArr);
        System.out.println(Arrays.toString(intArr));
        // 将arr每个值都改成3
        Arrays.fill(intArr, 3);
        System.out.println(Arrays.toString(intArr));

        test();
    }

    /**
     * 打印杨辉三角
     * @param num
     */
    static void printYanghui(int num){

        //1.声明并初始化二维数组
        int[][] yangHui = new int[num][];

        //2.给数组的元素赋值
        for(int i = 0;i < yangHui.length;i++){
            yangHui[i] = new int[i + 1];

            //2.1 给首末元素赋值
            yangHui[i][0] = yangHui[i][i] = 1;
            //2.2 给每行的非首末元素赋值
            for(int j = 1;j < yangHui[i].length - 1;j++){
                yangHui[i][j] = yangHui[i-1][j-1] + yangHui[i-1][j];
            }
        }

        //3.遍历二维数组
        for(int i = 0;i < yangHui.length;i++){
            for(int j = 0;j < yangHui[i].length;j++){
                System.out.print(yangHui[i][j] + "  ");
            }
            System.out.println();
        }
    }

    static void test(){
        Object o1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(o1);//
        Object o2;
        if (true)
            o2 = new Integer(1);
        else
            o2 = new Double(2.0);
        System.out.println(o2);//
    }
}

class Something {
    int age;
    {
        age = 10;
    }
    public static void main(String[] something_to_do) {
        System.out.println("Do something ...");
    }
}

final class FinalClass{
    final static int COUNT = 0;
}
