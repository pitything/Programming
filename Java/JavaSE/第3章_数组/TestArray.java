package 第3章_数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestArray {

    public static void main(String [] args) {
        /** 一维数组 */
        /** 声明 */
        int[] a;
        int b [];
        /** 初始化 */
        /** 动态初始化 */
        int[] c = new int[3];
        c[0] = 0;
        c[1] = 1;
        boolean[] ddd = new boolean[3];
        System.out.println(ddd[0]);
        /** 静态初始化 */
        int[] d = {1, 2, 3};
        int[] e = new int[]{1, 2, 3};

        /** 二维数组 */
        /** 声明 */
        int [][] aa;
        int bb [][];
        /** 初始化 */
        /** 动态初始化 */
        aa = new int [3][3];
        bb = new int [3][];
//        bb = new int [][3]; // 非法

        /* 特殊写法:x为一维数组；y为二维数组 */
        int[] x, y[];
        x = new int[3];
        y = new int[1][];

        /** 打印杨辉三角 */
        printYanghui(10);

        /** Arrays类常用方法 */
        System.out.println("------------Arrays类常用方法------------");
        int[] a1 = new int[] {3, 4, 2, 1, 7};
        int[] a2 = new int[] {3, 4, 2, 1, 7};
        System.out.println(Arrays.equals(a1, a2)); // true，比较两个数组是否一样
        System.out.println(Arrays.toString(a1)); // [3, 4, 2, 1, 7]，输出数组元素

        Arrays.fill(a1, 3); // 将数组每个值都改成3
        System.out.println(Arrays.toString(a1)); // [3, 3, 3, 3, 3]

        Arrays.sort(a2); // 排序
        System.out.println(Arrays.toString(a2)); // [1, 2, 3, 4, 7]
        System.out.println(Arrays.binarySearch(a2, 3)); // 2，二分查找，返回下标

        System.out.println(Arrays.toString(Arrays.copyOf(a2, 3))); // 数组复制 3个元素：[1, 2, 3]
        System.out.println(Arrays.toString(Arrays.copyOfRange(a2, 2, 4))); // 数组复制 [2，4)区间：[3, 4]

        int[] intArr = new int[]{1, 3, 2, 45, 23, 32};
        String[] strArr = new String[]{"中国","日本","美国"};

        /** int数组转list */
        List<Integer> intArrList = Arrays.stream(intArr).boxed().collect(Collectors.toList());
        System.out.println(intArrList);
        // String 数组转list
        // 注意Arrays.asList()返回的是ArrayList，这个是Arrays类下面的静态内部类，是不可变的
        List<String> strArrList = new ArrayList<>(Arrays.asList(strArr));
        List<String> strArrList2 = Arrays.stream(strArr).collect(Collectors.toList());
        System.out.println("strArrList: " + strArrList);
        System.out.println("strArrList2: " + strArrList2);

        /** list转数组 */
        List<Integer> intList = new ArrayList<Integer>(){{
            add(1);
            add(3);
            add(2);
            add(5);
            add(4);
        }};
        List<String> strlist = new ArrayList<String>() {{
            add("aa");
            add("bb");
            add("cc");
        }};
        Integer[] intlistArr1 = intList.toArray(new Integer[intList.size()]);
        int[] intlistArr2 = intList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(intlistArr1));
        System.out.println(Arrays.toString(intlistArr2));
        String[] stringlistArr = strlist.toArray(new String[strlist.size()]);
        System.out.println(Arrays.toString(stringlistArr));
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
}